package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.ReviewDao;
import by.rekuts.travelagency.dao.subjects.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Review review) {
        entityManager.persist(review);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Review.class, id));    //todo to test
    }

    @Override
    public Review getReviewById(int id) {
        return entityManager.find(Review.class, id);
    }

    @Override
    public List<Review> getAllReviews() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteriaQuery = builder.createQuery(Review.class);
        Root<Review> root = criteriaQuery.from(Review.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
