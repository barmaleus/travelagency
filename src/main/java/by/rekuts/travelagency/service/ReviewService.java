package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.ReviewDao;
import by.rekuts.travelagency.domain.Review;

import java.util.List;

/**
 * Interface ReviewService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of ReviewDao interface and could call its methods.
 */
public interface ReviewService {

    /**
     * This method calls {@link ReviewDao#insert(Review)}
     * It inserts object of Review class to database
     * @param review - object of the review
     * @see Review
     */
    void insert(Review review);

    /**
     * This method calls {@link ReviewDao#delete(int)}
     * It deletes review with id <b>id</b> from database
     * @param id - id of the review
     * @see Review#id
     */
    void delete(int id);

    /**
     * This method calls {@link ReviewDao#getReviewById(int)}
     * @param id - id of the review
     * @return object of the review with id <b>id</b> from database if it exist
     */
    Review getReviewById(int id);

    /**
     * This method calls {@link ReviewDao#getAllReviews()}
     * @return list of all reviews from database
     */
    List<Review> getAllReviews();
}
