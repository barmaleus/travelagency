package by.rekuts.travelagency.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.dao.subjects.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDaoImpl extends JdbcDaoSupport implements HotelDao {
    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(Hotel hotel) {
        String sql = "INSERT INTO hotel " +
                "(id, name, stars, website, latitude, longitude, features) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                hotel.getHotelId(), hotel.getName(), hotel.getStars(), hotel.getWebsite(), hotel.getLatitude(), hotel.getLongitude(), hotel.getFeatures()
        });
    }

    @Override
    public void delete(int id) {
        String sql ="DELETE FROM hotel WHERE id = ?";
        getJdbcTemplate().update(sql, new Object[]{id});
    }

    @Override
    public Hotel getHotelById(int id) {
        String sql = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel WHERE id = ?";
        return (Hotel)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Hotel>(){
            @Override
            public Hotel mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Hotel hotel = new Hotel();
                hotel.setHotelId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setStars(rs.getShort("stars"));
                hotel.setLatitude(rs.getDouble("latitude"));
                hotel.setLongitude(rs.getDouble("longitude"));
                hotel.setFeatures((List<Hotel.Features>)(rs.getArray("features")));
                return hotel;
            }
        });
    }

    @Override
    public List<Hotel> getAllHotels() {
        String sql = "SELECT id, name, stars, website, latitude, longitude, features FROM hotel";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Hotel> result = new ArrayList<>();
        for(Map<String, Object> row:rows){
            Hotel hotel = new Hotel();
            hotel.setHotelId((Integer)row.get("id"));
            hotel.setName((String)row.get("name"));
            hotel.setStars((Short)row.get("stars"));
            hotel.setLatitude((Double)row.get("latitude"));
            hotel.setLongitude((Double)row.get("longitude"));
            hotel.setFeatures((List<Hotel.Features>)row.get("features"));
        }
        return result;
    }
}
