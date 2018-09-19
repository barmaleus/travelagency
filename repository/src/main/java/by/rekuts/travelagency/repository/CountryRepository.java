package by.rekuts.travelagency.repository;

import java.util.List;

import by.rekuts.travelagency.domain.Country;

/**
 * Interface CountryRepository concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Country objects in database.
 */
public interface CountryRepository {

	/**
	 * This method inserts object of Country class to database
	 * @param country - object of the country
	 * @see Country
	 */
	void insert(Country country);

	/**
	 * This method updates object of country in database
	 * @param country - object of the country
	 */
	void update(Country country);

	/**
	 * This method deletes country with id <b>id</b> from database
	 * @param id - id of the country
	 * @see Country#id
	 */
	void delete(int id);

	/**
	 * @return list of all countries from database or by id
	 */
	List<Country> getList(Specification specification);
}
