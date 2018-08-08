package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.repository.ReviewRepository;
import by.rekuts.travelagency.repository.ReviewSpecification;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.domain.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Review review) {
        entityManager.persist(review);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Review.class, id));
    }

    @LogReturn
    @Override
    public List<Review> getList(Specification specification) {
        final ReviewSpecification reviewSpecification = (ReviewSpecification) specification;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = builder.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        List<Predicate> predicates;
        predicates = reviewSpecification.getPredicates(root, builder);

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
