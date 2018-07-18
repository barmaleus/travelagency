package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.impl.ReviewDaoImpl;
import by.rekuts.travelagency.dao.subjects.Review;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ReviewDaoImplTest {
    private static ReviewDao reviewDao;
    @ClassRule
    public static PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @BeforeClass
    public static void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db.getTestDatabase());
        reviewDao = new ReviewDaoImpl(jdbcTemplate);
//        try (Connection c = db.getTestDatabase().getConnection();
//             Statement stmt = c.createStatement()) {
//            stmt.execute(
//                    "delete from schema.table;\n" +
//                            "insert into schema.table\n" +
//                            "  (body, type, resource_uri, created, last_modified) values\n" +
//                            "  ('foo', 'TEXT', '/resource/uri', '2011-11-09', '2011-11-09');\n");
//        } catch (Exception e) {
//            fail();
//        }
    }

    @Test
    @Ignore
    public void insertTestTrue() throws SQLException {
        ReviewDao reviewDao = new ReviewDaoImpl(new JdbcTemplate(db.getTestDatabase()));
        Review review = new Review(18569, Timestamp.valueOf(LocalDateTime.now()), "Review text", 45, 19);
        reviewDao.insert(review);
        Connection c = db.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM review WHERE id=18569");
        rs.next();
        String reviewText = rs.getString("text");
        assertEquals(review.getText(), reviewText);
    }
}
