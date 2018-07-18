package by.rekuts.travelagency.dao;

import com.opentable.db.postgres.embedded.DatabaseConnectionPreparer;
import com.opentable.db.postgres.embedded.DatabasePreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDaoImplTest {
    private final DatabasePreparer prepCountry = new SimpleReviewPreparer("review");
    @Rule
    public PreparedDbRule dbCountry = EmbeddedPostgresRules.preparedDatabase(prepCountry);

    @Test
    public void insertTestTrue() {

    }
}

class SimpleReviewPreparer implements DatabaseConnectionPreparer {
    private final String name;

    SimpleReviewPreparer(String name) {
        this.name = name;
    }

    @Override
    public void prepare(Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                "CREATE TABLE %s (id integer NOT NULL, date date, text text, user_id integer, tour_id integer PRIMARY KEY (id), FOREIGN KEY)", name))) {
            stmt.execute();
        }
    }
}
