package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Country;

/**
 * Interface CountryDao concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Country objects in database.
 */
public interface CountryDao {

	/**
	 * This method inserts object of Country class to database
	 * @param country - object of the country
	 * @see Country
	 */
	void insert(Country country);

	/**
	 * This method deletes country with id <b>id</b> from database
	 * @param id - id of the country
	 * @see Country#id
	 */
	void delete(int id);

	/**
	 * @param id - id of the country
	 * @return object of the country with id <b>id</b> from database if it exist
	 */
	Country getCountryById(int id);

	/**
	 * @return list of all countries from database
	 */
	List<Country> getAllCountries();
}
