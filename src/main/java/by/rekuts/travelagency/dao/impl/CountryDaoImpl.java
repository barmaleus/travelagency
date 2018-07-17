package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("countrydao")
public class CountryDaoImpl implements CountryDao {
	JdbcTemplate jdbcTemplate;

	@Autowired
	public CountryDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(Country country) {
		String sql = "INSERT INTO country (id, name) VALUES (?, ?)" ;
		jdbcTemplate.update(sql, new Object[]{
				country.getId(), country.getName()
		});
	}

	@Override
	public void delete(int id) {
		String sql ="DELETE FROM country WHERE id = ?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public Country getCountryById(int id) {
		String sql = "SELECT id, name FROM country WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Country>(){
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
		String sql = "SELECT id, name FROM country";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
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
