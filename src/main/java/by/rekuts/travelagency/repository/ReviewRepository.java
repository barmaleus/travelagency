package by.rekuts.travelagency.repository;

import java.util.List;

import by.rekuts.travelagency.domain.Review;

/**
 * Interface ReviewService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Review objects in database.
 */
public interface ReviewRepository {

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
	 * @return list of reviews from database by specification
	 */
	List<Review> getList(Specification specification);
}
