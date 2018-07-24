package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.subjects.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class TourDaoImpl implements TourDao {
    private static final String INSERT_TOUR_QUERY = "INSERT INTO tour " +
            "(id, photo, date, duration, description, cost, tour_type, hotel_id, country_id) VALUES (?, ?, ?, ?, ?, CAST(? AS money), CAST(? AS tour_type), ?, ?)" ;
    private static final String DELETE_TOUR_QUERY = "DELETE FROM tour WHERE id = ?";
    private static final String GET_TOUR_BY_ID_QUERY = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour WHERE id = ?";
    private static final String GET_ALL_TOURS_QUERY = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TourDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
        return jdbcTemplate.queryForObject(GET_TOUR_BY_ID_QUERY, new Object[]{id}, (rs, rwNumber) -> {
            Tour tour = new Tour.TourBuilder(rs.getInt("id")).buildTour();
            tour.setPhoto(rs.getString("photo"));
            tour.setDate(rs.getDate("date").toLocalDate());
            tour.setDuration(rs.getInt("duration"));
            tour.setDescription(rs.getString("description"));
            tour.setCost(BigDecimal.valueOf(rs.getDouble("cost")));
            tour.setTourType(rs.getString("tour_type"));
            tour.setHotelId(rs.getInt("hotel_id"));
            tour.setCountryId(rs.getInt("country_id"));
            return tour;
        });
    }

    @Override
    public List<Tour> getAllTours() {
        return jdbcTemplate.query(
                GET_ALL_TOURS_QUERY,
                (resultSet, i) -> {
                    Tour tour = new Tour.TourBuilder(resultSet.getInt(1)).buildTour();
                    tour.setPhoto(resultSet.getString(2));
                    tour.setDate(resultSet.getDate(3).toLocalDate());
                    tour.setDuration(resultSet.getInt(4));
                    tour.setDescription(resultSet.getString(5));
                    tour.setCost(BigDecimal.valueOf(resultSet.getDouble(6)));
                    tour.setTourType(resultSet.getString(7));
                    tour.setHotelId(resultSet.getInt(8));
                    tour.setCountryId(resultSet.getInt(9));
                    return tour;
                }
        );
    }
}
