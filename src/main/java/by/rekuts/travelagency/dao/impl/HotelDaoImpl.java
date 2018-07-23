package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.dao.subjects.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao {
    private final static String INSERT_HOTEL_QUERY = "INSERT INTO hotel " +
            "(id, name, stars, website, latitude, longitude, features) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private final static String DELETE_HOTEL_QUERY = "DELETE FROM hotel WHERE id = ?";
    private final static String GET_HOTEL_BY_ID_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel WHERE id = ?";
    private final static String GET_ALL_HOTELS_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel";
    private final static Logger LOGGER = LoggerFactory.getLogger(HotelDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Hotel hotel) {
        Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
        try {
            Array featuresArray = connection.createArrayOf("features", hotel.getFeatures().toArray());
            jdbcTemplate.update(INSERT_HOTEL_QUERY, hotel.getHotelId(), hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(), hotel.getLongitude(), featuresArray);
        } catch (SQLException e) {
            LOGGER.warn("Insert operation is failed.");
        }
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_HOTEL_QUERY, id);
    }

    @Override
    public Hotel getHotelById(int id) {
        return jdbcTemplate.queryForObject(GET_HOTEL_BY_ID_QUERY, new Object[]{id}, (rs, rwNumber) -> {
            Hotel hotel = new Hotel();
            hotel.setHotelId(rs.getInt("id"));
            hotel.setName(rs.getString("name"));
            hotel.setStars(rs.getInt("stars"));
            hotel.setWebsite(rs.getString("website"));
            hotel.setLatitude(rs.getBigDecimal("latitude"));
            hotel.setLongitude(rs.getBigDecimal("longitude"));
            hotel.setFeatures(Arrays.asList((String[])rs.getArray("features").getArray()));
            return hotel;
        });
    }

    @Override
    public List<Hotel> getAllHotels() {
        return jdbcTemplate.query(
                GET_ALL_HOTELS_QUERY,
                (resultSet, i) -> {
                    Hotel hotel = new Hotel();
                    hotel.setHotelId(resultSet.getInt(1));
                    hotel.setName(resultSet.getString(2));
                    hotel.setStars(resultSet.getInt(3));
                    hotel.setWebsite(resultSet.getString(4));
                    hotel.setLatitude(resultSet.getBigDecimal(5));
                    hotel.setLongitude(resultSet.getBigDecimal(6));
                    hotel.setFeatures(Arrays.asList((String[])(resultSet.getArray(7)).getArray()));
                    return hotel;
                }
        );
    }
}
