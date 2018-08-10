package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.UserRepository;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        entityManager.persist(user);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @LogReturn
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
}
