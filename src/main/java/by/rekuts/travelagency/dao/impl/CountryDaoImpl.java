package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CountryDaoImpl implements CountryDao {
	private final static String INSERT_COUNTRY_QUERY = "INSERT INTO country (id, name) VALUES (?, ?)";
	private final static String DELETE_COUNTRY_QUERY = "DELETE FROM country WHERE id = ?";
	private final static String GET_COUNTRY_BY_ID_QUERY = "SELECT id, name FROM country WHERE id = ?";
	private final static String GET_ALL_COUNTRIES_QUERY = "SELECT id, name FROM country";
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
		return jdbcTemplate.queryForObject(GET_COUNTRY_BY_ID_QUERY, new Object[]{id}, new RowMapper<Country>(){
			@Override
			public Country mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Country country = new Country();
				country.setId(rs.getInt("id"));
				country.setName(rs.getString("name"));
				return country;
			}
		});
	}

	@Override
	public List<Country> getAllCountries() {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_COUNTRIES_QUERY);
		List<Country> result = new ArrayList<Country>();
		for(Map<String, Object> row:rows){
			Country country = new Country();
			country.setId((Integer)row.get("id"));
			country.setName((String)row.get("name"));
			result.add(country);
		}
		return result;
	}
	
	
}
