package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.subjects.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TourDaoImpl implements TourDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TourDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Tour tour) {
        String sql = "INSERT INTO tour " +
                "(id, photo, date, duration, description, cost, tour_type, hotel_id, country_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        jdbcTemplate.update(sql, new Object[]{
                tour.getId(), tour.getPhoto(), tour.getDate(), tour.getDuration(), tour.getDescription(), tour.getCost(),
                tour.getTourType(), tour.getHotelId(), tour.getCountryId()
        });
    }

    @Override
    public void delete(int id) {
        String sql ="DELETE FROM tour WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public Tour getTourById(int id) {
        String sql = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour WHERE id = ?";
        return (Tour) jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Tour>(){
            @Override
            public Tour mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Tour tour = new Tour();
                tour.setId(rs.getInt("id"));
                tour.setPhoto(rs.getString("photo"));
                tour.setDate(rs.getDate("date").toLocalDate());
                tour.setDuration(rs.getShort("duration"));
                tour.setDescription(rs.getString("description"));
                tour.setCost(rs.getInt("cost"));
//                tour.setTourType(rs.getString("tour_type"));     //todo
                tour.setHotelId(rs.getInt("hotel_id"));
                tour.setCountryId(rs.getInt("country_id"));
                return tour;
            }
        });
    }

    @Override
    public List<Tour> getAllTours() {
        String sql = "SELECT id, photo, date, duration, description, cost, tour_type, hotel_id, country_id FROM tour";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        List<Tour> result = new ArrayList<Tour>();
        for(Map<String, Object> row:rows){
            Tour tour = new Tour();
            tour.setId((Integer)row.get("id"));
            tour.setPhoto((String)row.get("photo"));
            tour.setDate((LocalDate)row.get("date"));
            tour.setDuration((Short)row.get("duration"));
            tour.setDescription((String)row.get("description"));
            tour.setCost((Integer)row.get("cost"));
//            tour.setTourType((String)row.get("tour_type"));     //todo
            tour.setHotelId((Integer)row.get("hotel_id"));
            tour.setCountryId((Integer)row.get("country_id"));
            result.add(tour);
        }
        return result;
    }
}
