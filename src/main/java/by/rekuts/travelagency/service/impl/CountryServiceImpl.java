package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryDao countryDao;
	
	@Override
	public void insert(Country country) {
		countryDao.insert(country);
	}

	@Override
	public void delete(int id) {
		countryDao.delete(id);		
	}

	@Override
	public Country getCountryById(int id) {
		return countryDao.getCountryById(id);
	}

	@Override
	public List<Country> getAllCountries() {
		return countryDao.getAllCountries();
	}
}
