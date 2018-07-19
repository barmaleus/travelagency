package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.subjects.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TourDaoImpl implements TourDao {
    private final static String INSERT_TOUR_QUERY = "INSERT INTO tour " +
            "(id, photo, date, duration, description, cost, tour_type, hotel_id, country_id) VALUES (?, ?, ?, ?, ?, CAST(? AS money), CAST(? AS tour_type), ?, ?)" ;
    private final static String DELETE_TOUR_QUERY = "DELETE FROM tour WHERE id = ?";
    private final static String GET_TOUR_BY_ID_QUERY = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour WHERE id = ?";
    private final static String GET_ALL_TOURS_QUERY = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour";
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
        return jdbcTemplate.queryForObject(GET_TOUR_BY_ID_QUERY, new Object[]{id}, new RowMapper<Tour>(){
            @Override
            public Tour mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Tour tour = new Tour();
                tour.setId(rs.getInt("id"));
                tour.setPhoto(rs.getString("photo"));
                tour.setDate(rs.getDate("date").toLocalDate());
                tour.setDuration(rs.getInt("duration"));
                tour.setDescription(rs.getString("description"));
                tour.setCost(new BigDecimal(rs.getDouble("cost")));
                tour.setTourType(rs.getString("tour_type"));
                tour.setHotelId(rs.getInt("hotel_id"));
                tour.setCountryId(rs.getInt("country_id"));
                return tour;
            }
        });
    }

    @Override
    public List<Tour> getAllTours() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(GET_ALL_TOURS_QUERY);

        List<Tour> result = new ArrayList<Tour>();
        for(Map<String, Object> row:rows){
            Tour tour = new Tour();
            tour.setId((Integer)row.get("id"));
            tour.setPhoto((String)row.get("photo"));
            tour.setDate(((Date)row.get("date")).toLocalDate());
            tour.setDuration((Integer) row.get("duration"));
            tour.setDescription((String)row.get("description"));
            tour.setCost(new BigDecimal((double)row.get("cost")));
            tour.setTourType((String)row.get("tour_type"));
            tour.setHotelId((Integer)row.get("hotel_id"));
            tour.setCountryId((Integer)row.get("country_id"));
            result.add(tour);
        }
        return result;
    }
}
