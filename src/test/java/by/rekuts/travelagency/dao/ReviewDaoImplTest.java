package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.impl.ReviewDaoImpl;
import by.rekuts.travelagency.dao.subjects.Review;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReviewDaoImplTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReviewDaoImplTest.class);

    private static ReviewDao reviewDao;
    @ClassRule
    public static PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @Test
    public void insertTestTrue() throws SQLException {
        reviewDao = new ReviewDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        Review review = new Review(18569, LocalDateTime.now(), "Review text", 45, 19);
        reviewDao.insert(review);
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE id=18569");
        rs.next();
        String reviewText = rs.getString("text");
        LOGGER.info("Test passed!!! - " + reviewText);
        assertEquals(review.getText(), reviewText);
    }

    @Test
    public void deleteTestTrue() throws SQLException {
        reviewDao = new ReviewDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rsFirst = stmt.executeQuery("SELECT count(*) FROM review");
        rsFirst.next();
        int countFirst = rsFirst.getInt("count");
        reviewDao.delete(1);
        ResultSet rsLast = stmt.executeQuery("SELECT count(*) FROM review");
        rsLast.next();
        int countLast = rsLast.getInt("count");
        LOGGER.info("Test passed!!! - " + (countFirst - countLast));
        assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getReviewByIdTest() throws SQLException {
        reviewDao = new ReviewDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        String reviewText = reviewDao.getReviewById(1).getText();
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE id=1");
        rs.next();
        String expectedText = rs.getString("text");
        LOGGER.info("Test passed!!! - " + reviewText);
        assertEquals(expectedText, reviewText);
    }

    @Test
    public void getAllReviewTest() throws SQLException {
        reviewDao = new ReviewDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        List<Review> reviews = reviewDao.getAllReviews();
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM review");
        rs.next();
        int expectedCount = rs.getInt("count");
        LOGGER.info("Test passed!!! - " + reviews.size());
        assertEquals(expectedCount, reviews.size());
    }
}
