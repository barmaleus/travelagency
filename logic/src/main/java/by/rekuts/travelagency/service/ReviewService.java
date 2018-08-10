package by.rekuts.travelagency.service;

import by.rekuts.travelagency.repository.ReviewRepository;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.domain.Review;

import java.util.List;

/**
 * Interface ReviewService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of ReviewRepository interface and could call its methods.
 */
public interface ReviewService {

    /**
     * This method calls {@link ReviewRepository#insert(Review)}
     * It inserts object of Review class to database
     * @param review - object of the review
     * @see Review
     */
    void insert(Review review);

    /**
     * This method calls {@link ReviewRepository#delete(int)}
     * It deletes review with id <b>id</b> from database
     * @param id - id of the review
     * @see Review#id
     */
    void delete(int id);

    /**
     * This method calls {@link ReviewRepository#getList(Specification)}
     * @return list reviews from database by criteria (review id, user id, tour id or all together)
     */
    List<Review> getList(Specification specification);
}
