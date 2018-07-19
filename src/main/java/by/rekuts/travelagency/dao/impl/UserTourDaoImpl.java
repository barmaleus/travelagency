package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.UserTourDao;
import by.rekuts.travelagency.dao.subjects.Tour;
import by.rekuts.travelagency.dao.subjects.User;
import by.rekuts.travelagency.dao.subjects.UserTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserTourDaoImpl implements UserTourDao {
    private final static String INSERT_USER_TOUR_QUERY = "INSERT INTO user_tour (user_id, tour_id) VALUES (?, ?)";
    private final static String DELETE_USER_TOUR_QUERY = "DELETE FROM user_tour WHERE user_id = ? & tour_id = ?";    //todo check query if right
    private final static String GET_USERS_BY_TOUR_ID_QUERY = "SELECT id, login, password FROM user, user_tour WHERE user_tour.tour_id = ? & user_tour.user_id = user.id";    //todo check query if right
    private final static String GET_TOURS_BY_USER_ID_QUERY = "SELECT id, login, password FROM user, user_tour WHERE user_tour.tour_id = ? & user_tour.user_id = user.id";    //todo rebuild query
    private final static String GET_ALL_USER_TOURS_QUERY = "SELECT user_id, tour_id FROM user_tour";   //todo check query if right
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserTourDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(UserTour userTour) {
        jdbcTemplate.update(INSERT_USER_TOUR_QUERY, userTour.getUserId(), userTour.getTourId());
    }

    @Override
    public void delete(int userId, int tourId) {
        jdbcTemplate.update(DELETE_USER_TOUR_QUERY, userId, tourId);
    }

    @Override
    public List<User> getUsersByTourId(int tourId) {
        return (List<User>) jdbcTemplate.queryForObject(GET_USERS_BY_TOUR_ID_QUERY, new Object[]{tourId}, new RowMapper<User>(){
            @Override
            public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }

    @Override
    public List<Tour> getToursByUserId(int userId) {
        return null;    //todo
    }

    @Override
    public List<UserTour> getAllUserTours() {
        return null;    //todo
    }
}
