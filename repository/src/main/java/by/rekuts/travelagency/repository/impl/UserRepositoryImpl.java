package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.UserRepository;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserSpecification;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

//    @LogReturn todo
    @Override
    public List<User> getList(Specification specification) {
        UserSpecification userSpecification = (UserSpecification) specification;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
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
        List<Tour> tours = user.getTours();
        if (!tours.contains(tour)) {
            tours.add(tour);
        }
        user.setTours(tours);
        entityManager.merge(user);
    }

    public void removeTourFromFavorites(User user, Tour tour) {
        List<Tour> tours = user.getTours();
        tours.remove(tour);
        user.setTours(tours);
        entityManager.merge(user);
    }
}
