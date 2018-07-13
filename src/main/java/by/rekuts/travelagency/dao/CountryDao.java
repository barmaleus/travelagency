package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Country;

public interface CountryDao {
	void insert(Country country);
	void delete(int id);
	Country getCountryById(int id);
	List<Country> getAllCountries();
}
