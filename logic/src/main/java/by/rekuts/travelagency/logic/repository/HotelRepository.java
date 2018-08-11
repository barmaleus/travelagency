package by.rekuts.travelagency.logic.repository;

import java.util.List;

import by.rekuts.travelagency.logic.domain.Hotel;

/**
 * Interface HotelService concerns to DAO layer .
 * Realisations of this interface do some CRUD operations with Hotel objects in database.
 */
public interface HotelRepository {

	/**
	 * This method inserts object of Hotel class to database
	 * @param hotel - object of the hotel
	 * @see Hotel
	 */
	void insert(Hotel hotel);

	/**
	 * This method deletes hotel with id <b>id</b> from database
	 * @param id - id of the hotel
	 * @see Hotel#id
	 */
	void delete(int id);

	/**
	 * @return list of all hotels from database or list with one hotel selected by id
	 */
	List<Hotel> getList(Specification specification);
}
