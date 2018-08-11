package by.rekuts.travelagency.logic.service;

import java.util.List;

import by.rekuts.travelagency.logic.repository.CountryRepository;
import by.rekuts.travelagency.logic.repository.Specification;
import by.rekuts.travelagency.logic.domain.Country;

/**
 * Interface CountryService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of CountryRepository interface and could call its methods.
 */
public interface CountryService {

	/**
	 * This method calls {@link CountryRepository#insert(Country)}
	 * It inserts object of Country class to database
	 * @param country - object of the country
	 * @see Country
	 */
	void insert(Country country);

	/**
	 * This method calls {@link CountryRepository#delete(int)}
	 * It deletes country with id <b>id</b> from database
	 * @param id - id of the country
	 * @see Country#id
	 */
	void delete(int id);

	/**
	 * This method calls {@link CountryRepository#getList(Specification)}
	 * @return list of all countries from database or list with one country selected by id
	 */
	List<Country> getList(Specification specification);
}
