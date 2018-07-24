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
    private static final String INSERT_HOTEL_QUERY = "INSERT INTO hotel " +
            "(id, name, stars, website, latitude, longitude, features) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private static final String DELETE_HOTEL_QUERY = "DELETE FROM hotel WHERE id = ?";
    private static final String GET_HOTEL_BY_ID_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel WHERE id = ?";
    private static final String GET_ALL_HOTELS_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel";
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelDaoImpl.class);
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
            int hotelId = rs.getInt("id");
            String hotelName = rs.getString("name");
            return new Hotel.HotelBuilder(hotelId, hotelName)
                    .withOptionalStars(rs.getInt("stars"))
                    .withOptionalWebsite(rs.getString("website"))
                    .withOptionalLocation(rs.getBigDecimal("latitude"), rs.getBigDecimal("longitude"))
                    .withOptionalFeatures(Arrays.asList((String[])rs.getArray("features").getArray()))
                    .buildHotel();
        });
    }

    @Override
    public List<Hotel> getAllHotels() {
        return jdbcTemplate.query(
                GET_ALL_HOTELS_QUERY,
                (resultSet, i) -> {
                    int hotelId = resultSet.getInt(1);
                    String hotelName = resultSet.getString(2);
                    return new Hotel.HotelBuilder(hotelId, hotelName)
                            .withOptionalStars(resultSet.getInt(3))
                            .withOptionalWebsite(resultSet.getString(4))
                            .withOptionalLocation(resultSet.getBigDecimal(5), resultSet.getBigDecimal(6))
                            .withOptionalFeatures(Arrays.asList((String[])(resultSet.getArray(7)).getArray()))
                            .buildHotel();
                }
        );
    }
}
