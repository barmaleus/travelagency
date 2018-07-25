package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Hotel;

/**
 * Interface HotelService concerns to DAO layer .
 * Realisations of this interface do some CRUD operations with Hotel objects in database.
 */
public interface HotelDao {

	/**
	 * This method inserts object of Hotel class to database
	 * @param hotel - object of the hotel
	 * @see Hotel
	 */
	void insert(Hotel hotel);

	/**
	 * This method deletes hotel with id <b>id</b> from database
	 * @param id - id of the hotel
	 * @see Hotel#hotelId
	 */
	void delete(int id);

	/**
	 * @param id - id of the hotel
	 * @return object of the hotel with id <b>id</b> from database if it exist
	 */
	Hotel getHotelById(int id);

	/**
	 * @return list of all hotels from database
	 */
	List<Hotel> getAllHotels();
}
