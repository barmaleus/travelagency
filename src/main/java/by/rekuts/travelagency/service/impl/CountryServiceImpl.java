package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryDao countryDao;

	@Transactional
	@Override
	public void insert(Country country) {
		countryDao.insert(country);
	}

	@Transactional
	@Override
	public void delete(int id) {
		countryDao.delete(id);
	}

	@Transactional
	@Override
	public Country getCountryById(int id) {
		return countryDao.getCountryById(id);
	}

	@Transactional
	@Override
	public List<Country> getAllCountries() {
		return countryDao.getAllCountries();
	}
}
