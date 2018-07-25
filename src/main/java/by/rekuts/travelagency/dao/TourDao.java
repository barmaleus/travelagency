package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Tour;

/**
 * Interface TourService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Tour objects in database.
 */
public interface TourDao {

	/**
	 * This method inserts object of Tour class to database
	 * @param tour - object of the tour
	 * @see Tour
	 */
	void insert(Tour tour);

	/**
	 * This method deletes tour with id <b>id</b> from database
	 * @param id - id of the tour
	 * @see Tour#id
	 */
	void delete(int id);

	/**
	 * @param id - id of the tour
	 * @return object of the tour with id <b>id</b> from database if it exist
	 */
	Tour getTourById(int id);

	/**
	 * @return list of all tours from database
	 */
	List<Tour> getAllTours();
}
