package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.repository.ReviewRepository;
import by.rekuts.travelagency.repository.ReviewSpecification;
import by.rekuts.travelagency.repository.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
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
    public void update(Review review) {
        var dbReview = entityManager.find(Review.class, review.getId());
        dbReview.setText(review.getText());
        dbReview.setUser(review.getUser());
        dbReview.setTour(review.getTour());
        entityManager.merge(dbReview);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Review.class, id));
    }

    @Override
    public List<Review> getList(Specification specification) {
        final var reviewSpecification = (ReviewSpecification) specification;
        var builder = entityManager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Review.class);
        var root = criteriaQuery.from(Review.class);
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
