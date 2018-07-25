package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.dao.subjects.Hotel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HotelDaoImpl implements HotelDao {
    private static final String INSERT_HOTEL_QUERY = "INSERT INTO hotel " +
            "(id, name, stars, website, latitude, longitude, features) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private static final String DELETE_HOTEL_QUERY = "DELETE FROM hotel WHERE id = ?";
    private static final String GET_HOTEL_BY_ID_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel WHERE id = ?";
    private static final String GET_ALL_HOTELS_QUERY = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Hotel hotel) {
        Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
        try {
            Array featuresArray = connection.createArrayOf("features", hotel.getFeatures().toArray());
            jdbcTemplate.update(INSERT_HOTEL_QUERY, hotel.getHotelId(), hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(), hotel.getLongitude(), featuresArray);
        } catch (SQLException e) {
            log.warn("Insert operation is failed.");
        }
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_HOTEL_QUERY, id);
    }

    @Override
    public Hotel getHotelById(int id) {
        return jdbcTemplate.queryForObject(
                GET_HOTEL_BY_ID_QUERY,
                new Object[]{id},
                (resultSet, rwNumber) -> Hotel.builder()
                .hotelId(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .stars(resultSet.getInt("stars"))
                .website(resultSet.getString("website"))
                .latitude(resultSet.getBigDecimal("latitude"))
                .longitude(resultSet.getBigDecimal("longitude"))
                .features(Arrays.asList((String[])resultSet.getArray("features").getArray()))
                .build());
    }

    @Override
    public List<Hotel> getAllHotels() {
        return jdbcTemplate.query(
                GET_ALL_HOTELS_QUERY,
                (resultSet, i) -> Hotel.builder()
                        .hotelId(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .stars(resultSet.getInt("stars"))
                        .website(resultSet.getString("website"))
                        .latitude(resultSet.getBigDecimal("latitude"))
                        .longitude(resultSet.getBigDecimal("longitude"))
                        .features(Arrays.asList((String[])resultSet.getArray("features").getArray()))
                        .build()
        );
    }
}
