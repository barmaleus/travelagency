package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.impl.UserDaoImpl;
import by.rekuts.travelagency.dao.subjects.User;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
public class UserDaoImplTest {

    private static UserDao userDao;
    @ClassRule
    public static PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @Test
    public void insertTestTrue() throws SQLException {
        userDao = new UserDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        User user = new User(84592, "CoolLogin", "PrettyDificultPassword");
        userDao.insert(user);
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE \"user\".id=84592");
        rs.next();
        String userLogin = rs.getString("login");
        log.info("Test passed!!! - " + userLogin);
        assertEquals(user.getLogin(), userLogin);
    }

    @Test
    public void deleteTestTrue() throws SQLException {
        userDao = new UserDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rsFirst = stmt.executeQuery("SELECT count(*) FROM \"user\"");
        rsFirst.next();
        int countFirst = rsFirst.getInt("count");
        userDao.delete(86);
        ResultSet rsLast = stmt.executeQuery("SELECT count(*) FROM \"user\"");
        rsLast.next();
        int countLast = rsLast.getInt("count");
        log.info("Test passed!!! - " + (countFirst - countLast));
        assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getUserByIdTest() throws SQLException {
        userDao = new UserDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        String userLogin = userDao.getUserById(1).getLogin();
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"user\" WHERE id=1");
        rs.next();
        String expectedLogin = rs.getString("login");
        log.info("Test passed!!! - " + userLogin);
        assertEquals(expectedLogin, userLogin);
    }

    @Test
    public void getAllUsersTest() throws SQLException {
        userDao = new UserDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        List<User> users = userDao.getAllUsers();
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM \"user\"");
        rs.next();
        int expectedCount = rs.getInt("count");
        log.info("Test passed!!! - " + users.size());
        assertEquals(expectedCount, users.size());
    }
}
