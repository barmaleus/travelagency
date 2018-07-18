package by.rekuts.travelagency.service;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Country;

public interface CountryService {
	void insert(Country country);
	void delete(int id);
	Country getCountryById(int id);
	List<Country> getAllCountries();
}
