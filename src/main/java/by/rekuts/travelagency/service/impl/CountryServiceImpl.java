package by.rekuts.travelagency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.CountryService;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
	JdbcTemplate jdbcTemplate;

//	todo
//	public void setDataSource(DataSource ds) {
//		jdbcTemplate = new JdbcTemplate(ds);
//	}

	@Autowired
	public CountryServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

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
