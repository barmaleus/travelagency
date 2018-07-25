package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.UserDao;
import by.rekuts.travelagency.dao.subjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final String INSERT_USER_QUERY = "INSERT INTO \"user\" (\"id\", login, password) VALUES (?, ?, ?)" ;
    private static final String DELETE_USER_QUERY = "DELETE FROM \"user\" WHERE \"id\" = ?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT \"id\", login, password FROM \"user\" WHERE id = ?";
    private static final String GET_ALL_USERS_QUERY = "SELECT \"id\", login, password FROM \"user\"";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getUserId(), user.getLogin(), user.getPassword());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_USER_QUERY, id);
    }

    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, new Object[]{id}, (rs, rwNumber) -> {
            User user = new User();
            user.setUserId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            return user;
        });
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(
                GET_ALL_USERS_QUERY,
                (resultSet, i) -> {
                    User user = new User();
                    user.setUserId(resultSet.getInt(1));
                    user.setLogin(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    return user;
                }
        );
    }
}
