package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {
	private static final String INSERT_COUNTRY_QUERY = "INSERT INTO country (id, name) VALUES (?, ?)";
	private static final String DELETE_COUNTRY_QUERY = "DELETE FROM country WHERE id = ?";
	private static final String GET_COUNTRY_BY_ID_QUERY = "SELECT id, name FROM country WHERE id = ?";
	private static final String GET_ALL_COUNTRIES_QUERY = "SELECT id, name FROM country";
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CountryDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Country country) {
		jdbcTemplate.update(INSERT_COUNTRY_QUERY, country.getId(), country.getName());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update(DELETE_COUNTRY_QUERY, id);
	}

	@Override
	public Country getCountryById(int id) {
		return jdbcTemplate.queryForObject(GET_COUNTRY_BY_ID_QUERY, new Object[]{id}, (rs, rwNumber) -> {
			Country country = new Country();
			country.setId(rs.getInt("id"));
			country.setName(rs.getString("name"));
			return country;
		});
	}

	@Override
	public List<Country> getAllCountries() {
		return jdbcTemplate.query(
				GET_ALL_COUNTRIES_QUERY,
				(resultSet, i) -> {
					Country country = new Country();
					country.setId(resultSet.getInt(1));
					country.setName(resultSet.getString(2));
					return country;
				}
		);
	}
	
	
}
