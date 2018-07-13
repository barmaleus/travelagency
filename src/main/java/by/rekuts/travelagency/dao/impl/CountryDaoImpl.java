package by.rekuts.travelagency.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;

@Repository
public class CountryDaoImpl extends JdbcDaoSupport implements CountryDao {
    @Qualifier("dataSource")
    @Autowired
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	@Override
	public void insert(Country country) {
		String sql = "INSERT INTO country (id, name) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				country.getId(), country.getName()
		});
	}

	@Override
	public void delete(int id) {
		String sql ="DELETE FROM country WHERE id = ?";
		getJdbcTemplate().update(sql, new Object[]{id});
	}

	@Override
	public Country getCountryById(int id) {
		String sql = "SELECT id, name FROM country WHERE id = ?";
		return (Country)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Country>(){
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
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
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
