package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.subjects.Tour;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TourDaoImpl implements TourDao {
    private static final String INSERT_TOUR_QUERY = "INSERT INTO tour " +
            "(id, photo, \"date\", duration, description, cost, tour_type, hotel_id, country_id) VALUES (?, ?, ?, ?, ?, CAST(? AS money), CAST(? AS tour_type), ?, ?)" ;
    private static final String DELETE_TOUR_QUERY = "DELETE FROM tour WHERE id = ?";
    private static final String GET_TOUR_BY_ID_QUERY = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour WHERE id = ?";
    private static final String GET_ALL_TOURS_QUERY = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Tour tour) {
        jdbcTemplate.update(INSERT_TOUR_QUERY, tour.getId(), tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(), tour.getCost(),
                tour.getTourType(), tour.getHotelId(), tour.getCountryId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_TOUR_QUERY, id);
    }

    @Override
    public Tour getTourById(int id) {
        return jdbcTemplate.queryForObject(GET_TOUR_BY_ID_QUERY,
                new Object[]{id},
                (resultSet, rwNumber) -> Tour.builder()
                .hotelId(resultSet.getInt("id"))
                .photo(resultSet.getString("photo"))
                .date(resultSet.getDate("date").toLocalDate())
                .duration(resultSet.getInt("duration"))
                .description(resultSet.getString("description"))
                .cost(BigDecimal.valueOf(resultSet.getDouble("cost")))
                .tourType(resultSet.getString("tour_type"))
                .hotelId(resultSet.getInt("hotel_id"))
                .countryId(resultSet.getInt("country_id"))
                .build());
    }

    @Override
    public List<Tour> getAllTours() {
        return jdbcTemplate.query(
                GET_ALL_TOURS_QUERY,
                (resultSet, i) -> Tour.builder()
                        .hotelId(resultSet.getInt("id"))
                        .photo(resultSet.getString("photo"))
                        .date(resultSet.getDate("date").toLocalDate())
                        .duration(resultSet.getInt("duration"))
                        .description(resultSet.getString("description"))
                        .cost(BigDecimal.valueOf(resultSet.getDouble("cost")))
                        .tourType(resultSet.getString("tour_type"))
                        .hotelId(resultSet.getInt("hotel_id"))
                        .countryId(resultSet.getInt("country_id"))
                        .build());
    }
}
