package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Review;

/**
 * Interface ReviewService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Review objects in database.
 */
public interface ReviewDao {

	/**
	 * This method inserts object of Review class to database
	 * @param review - object of the review
	 * @see Review
	 */
	void insert(Review review);

	/**
	 * This method deletes review with id <b>id</b> from database
	 * @param id - id of the review
	 * @see Review#id
	 */
	void delete(int id);

	/**
	 * @param id - id of the review
	 * @return object of the review with id <b>id</b> from database if it exist
	 */
	Review getReviewById(int id);

	/**
	 * @return list of all reviews from database
	 */
	List<Review> getAllReviews();
}
