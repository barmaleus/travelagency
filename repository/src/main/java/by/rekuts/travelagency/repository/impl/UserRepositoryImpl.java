package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.domain.aspects.LogReturn;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.UserRepository;
import by.rekuts.travelagency.repository.UserSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(User user) {
        if(user.getRole() == null) {
            user.setRole(User.UserRole.ROLE_MEMBER);
        }
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        var dbUser = entityManager.find(User.class, user.getId());
        dbUser.setLogin(user.getLogin());
        dbUser.setPassword(user.getPassword());
        dbUser.setRole(user.getRole());
        entityManager.merge(dbUser);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @LogReturn
    @Override
    public List<User> getList(Specification specification) {
        var userSpecification = (UserSpecification) specification;
        var builder = entityManager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        List<Predicate> predicates;
        predicates = userSpecification.getPredicates(root, builder);

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void addTourToFavorites(User user, Tour tour) {
        var tours = user.getTours();
        if (!tours.contains(tour)) {
            tours.add(tour);
        }
        user.setTours(tours);
        entityManager.merge(user);
    }

    public void removeTourFromFavorites(User user, Tour tour) {
        var tours = user.getTours();
        tours.remove(tour);
        user.setTours(tours);
        entityManager.merge(user);
    }
}
