package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.impl.TourDaoImpl;
import by.rekuts.travelagency.dao.subjects.Tour;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TourDaoImplTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(TourDaoImplTest.class);
    private static TourDao tourDao;
    @ClassRule
    public static PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @Test
    public void insertTestTrue() throws SQLException {
        tourDao = new TourDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        Tour tour = new Tour(9845, "photo.jpg", LocalDate.now(), 14, "Interesting tour", new BigDecimal(900), Tour.TourType.J.getValue(), 85, 67);
        tourDao.insert(tour);
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tour WHERE id=9845");
        rs.next();
        String tourText = rs.getString("description");
        LOGGER.info("Test passed!!! - " + tourText);
        assertEquals(tour.getDescription(), tourText);
    }

    @Test
    public void deleteTestTrue() throws SQLException {
        tourDao = new TourDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rsFirst = stmt.executeQuery("SELECT count(*) FROM tour");
        rsFirst.next();
        int countFirst = rsFirst.getInt("count");
        tourDao.delete(48);
        ResultSet rsLast = stmt.executeQuery("SELECT count(*) FROM tour");
        rsLast.next();
        int countLast = rsLast.getInt("count");
        LOGGER.info("Test passed!!! - " + (countFirst - countLast));
        assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getTourByIdTest() throws SQLException {
        tourDao = new TourDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        String tourText = tourDao.getTourById(1).getDescription();
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tour WHERE id=1");
        rs.next();
        String expectedText = rs.getString("description");
        LOGGER.info("Test passed!!! - " + tourText);
        assertEquals(expectedText, tourText);
    }

    @Test
    public void getAllToursTest() throws SQLException {
        tourDao = new TourDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        List<Tour> tours = tourDao.getAllTours();
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM tour");
        rs.next();
        int expectedCount = rs.getInt("count");
        LOGGER.info("Test passed!!! - " + tours.size());
        assertEquals(expectedCount, tours.size());
    }
}
