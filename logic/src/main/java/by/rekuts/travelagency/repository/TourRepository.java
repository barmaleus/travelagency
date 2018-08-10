package by.rekuts.travelagency.repository;

import java.util.List;

import by.rekuts.travelagency.domain.Tour;

/**
 * Interface TourService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Tour objects in database.
 */
public interface TourRepository {

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
	 * @return list of tours from database by specification (tour id, country id, tour date, duration,
	 * type of vacation, minimal and maximal cost, stars of the hotel, user id)
	 */
	List<Tour> getList(Specification specification);
}
