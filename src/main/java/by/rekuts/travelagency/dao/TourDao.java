package by.rekuts.travelagency.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Tour;

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

    /**
     * This method returns list of tours by parameters
     * @param country - object of country
     * @param date - date in LocalDate format
     * @param duration - duration of tour, days
     * @param tourType - type of travel
     * @param minCost - lower bound of price
     * @param maxCost - upper bound of price
     * @param stars - hotel stars
     * @return - list of tours by specified parameters
     */
	List<Tour> getToursByCriteria(Country country, LocalDate date, Integer duration,
                                  Tour.TourType tourType, BigDecimal minCost, BigDecimal maxCost, Integer stars);

    /**
     * @return list of tours from database for a specified user by his id
     */
	List<Tour> getToursByUserId(int userId);
}
