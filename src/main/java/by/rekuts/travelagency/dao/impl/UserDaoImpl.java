package by.rekuts.travelagency.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import by.rekuts.travelagency.dao.UserDao;
import by.rekuts.travelagency.dao.subjects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO user " +
                "(id, login, password) VALUES (?, ?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                user.getUserId(), user.getLogin(), user.getPassword()
        });
    }

    @Override
    public void delete(int id) {
        String sql ="DELETE FROM user WHERE id = ?";
        getJdbcTemplate().update(sql, new Object[]{id});
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT id, login, password FROM user WHERE id = ?";
        return (User)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<User>(){
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
    public List<User> getAllUsers() {
        String sql = "SELECT id, login, password FROM country";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<User> result = new ArrayList<User>();
        for(Map<String, Object> row:rows){
            User user = new User();
            user.setUserId((Integer)row.get("id"));
            user.setLogin((String)row.get("login"));
            user.setPassword((String)row.get("password"));
            result.add(user);
        }
        return result;
    }
}
