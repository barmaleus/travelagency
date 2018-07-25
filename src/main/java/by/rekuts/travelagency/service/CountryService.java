package by.rekuts.travelagency.service;

import java.util.List;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;

/**
 * Interface CountryService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of CountryDao interface and could call its methods.
 */
public interface CountryService {

	/**
	 * This method calls {@link CountryDao#insert(Country)}
	 * It inserts object of Country class to database
	 * @param country - object of the country
	 * @see Country
	 */
	void insert(Country country);

	/**
	 * This method calls {@link CountryDao#delete(int)}
	 * It deletes country with id <b>id</b> from database
	 * @param id - id of the country
	 * @see Country#id
	 */
	void delete(int id);

	/**
	 * This method calls {@link CountryDao#getCountryById(int)}
	 * @param id - id of the country
	 * @return object of the country with id <b>id</b> from database if it exist
	 */
	Country getCountryById(int id);

	/**
	 * This method calls {@link CountryDao#getAllCountries()}
	 * @return list of all countries from database
	 */
	List<Country> getAllCountries();
}
