package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.impl.CountryDaoImpl;
import by.rekuts.travelagency.dao.subjects.Country;
import com.opentable.db.postgres.embedded.DatabaseConnectionPreparer;
import com.opentable.db.postgres.embedded.DatabasePreparer;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class CountryDaoImplTest {

	private final DatabasePreparer prepCountry = new SimpleCountryPreparer("country");
	@Rule
	public PreparedDbRule dbCountry = EmbeddedPostgresRules.preparedDatabase(prepCountry);

    @Test
    public void testEmbeddedDbEcho() throws SQLException, IOException
    {
        EmbeddedPostgres pg = EmbeddedPostgres.start();
        Connection c = pg.getPostgresDatabase().getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT 1");
        assertTrue(rs.next());
        assertEquals(1, rs.getInt(1));
        assertFalse(rs.next());
    }

    @Test
    public void testDbSimpleQuery() throws SQLException {
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1', 'Country Simple Name')");
        ResultSet rs = stmt.executeQuery("SELECT * FROM country");
        rs.next();
        assertEquals(1, rs.getInt("id"));
    }

    @Test
    public void insertTest() throws SQLException {
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Country country = new Country(375, "Country Name");
        countryDao.insert(country);
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM country");
        rs.next();
        String countryName = rs.getString("name");
        assertEquals(country.getName(), countryName);
    }

    @Test
    public void deleteTestTrue() throws SQLException{
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Country country = new Country(3750, "Great Country");
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('3750', 'Country Simple Name')");
        countryDao.delete(3750);
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM country");
        rs.next();
        int count = rs.getInt("count");
        assertEquals(0, count);
    }

    @Test
    public void deleteTestFalse() throws SQLException{
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Country country = new Country(3750, "Great Country");
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('3750', 'Country Simple Name')");
        countryDao.delete(country.getId());
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM country");
        rs.next();
        int count = rs.getInt("count");
        assertNotEquals(1, count);
    }

    @Test
    public void getCountryByIdTestTrue() throws SQLException {
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1850', 'Country Just Country')");
        Country country = countryDao.getCountryById(1850);
        assertEquals("Country Just Country", country.getName());
    }

    @Test
    public void getCountryByIdTestFalse() throws SQLException {
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1850', 'Country Just Country')");
        Country country = countryDao.getCountryById(1850);
        assertNotEquals("", country.getName());
    }

    @Test
    public void getAllCountriesTestTrue() throws SQLException {
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1851', 'Country First')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('18298', 'Country Second')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('6582', 'Country Third')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('458', 'Country Fourth')");
        List<Country> countries = countryDao.getAllCountries();
        assertEquals(4, countries.size());
    }

    @Test
    public void getAllCountriesTestFalse() throws SQLException {
        CountryDao countryDao = new CountryDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1851', 'Country First')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('18298', 'Country Second')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('1888', 'Country Third')");
        List<Country> countries = countryDao.getAllCountries();
        assertNotEquals(0, countries.size());
    }
}

class SimpleCountryPreparer implements DatabaseConnectionPreparer {
    private final String name;

    SimpleCountryPreparer(String name) {
        this.name = name;
    }

    @Override
    public void prepare(Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                "CREATE TABLE %s (id int NOT NULL, name text NOT NULL, PRIMARY KEY (id))", name))) {
            stmt.execute();
        }
    }
}
