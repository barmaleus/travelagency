package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.dao.subjects.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class HotelDaoImpl implements HotelDao {
    private final static String INSERT_HOTEL_QUERY = "INSERT INTO hotel " +
            "(id, name, stars, website, latitude, longitude, features) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private final static String DELETE_HOTEL_QUERY = "DELETE FROM hotel WHERE id = ?";
    private final static String GET_HOTEL_BY_ID_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel WHERE id = ?";
    private final static String GET_ALL_HOTELS_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Hotel hotel) {
        jdbcTemplate.update(INSERT_HOTEL_QUERY, new Object[]{
                hotel.getHotelId(), hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(), hotel.getLongitude(), hotel.getFeatures()
        });
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_HOTEL_QUERY, new Object[]{id});
    }

    @Override
    public Hotel getHotelById(int id) {
        return jdbcTemplate.queryForObject(GET_HOTEL_BY_ID_QUERY, new Object[]{id}, new RowMapper<Hotel>(){
            @Override
            public Hotel mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setStars(rs.getInt("stars"));
                hotel.setWebsite(rs.getString("website"));
                hotel.setLatitude(rs.getBigDecimal("latitude"));
                hotel.setLongitude(rs.getBigDecimal("longitude"));
                hotel.setFeatures(rs.getArray("features"));
                return hotel;
            }
        });
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_HOTELS_QUERY);

        List<Hotel> result = new ArrayList<>();
        for(Map<String, Object> row:rows){
            Hotel hotel = new Hotel();
            hotel.setHotelId((Integer)row.get("id"));
            hotel.setName((String)row.get("name"));
            hotel.setStars((Integer)row.get("stars"));
            hotel.setWebsite((String)row.get("website"));
            hotel.setLatitude((BigDecimal)row.get("latitude"));
            hotel.setLongitude((BigDecimal)row.get("longitude"));
            hotel.setFeatures((Array)row.get("features"));
            result.add(hotel);
        }
        return result;
    }
}
