package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.impl.CountryServiceImpl;
import com.opentable.db.postgres.embedded.DatabaseConnectionPreparer;
import com.opentable.db.postgres.embedded.DatabasePreparer;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class CountryServiceImplTest {
    private final DatabasePreparer prepCountry = new SimplePreparer("country");
    @Rule
    public PreparedDbRule dbCountry = EmbeddedPostgresRules.preparedDatabase(prepCountry);

    @Autowired
    CountryDao countryDao;

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
    @Ignore
    public void insertTest() throws SQLException {
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Country country = new Country(375, "Country Name");
        countryService.insert(country);
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM country");
        rs.next();
        String countryName = rs.getString("name");
        assertEquals(country.getName(), countryName);
    }

    @Test
    @Ignore
    public void deleteTestTrue() throws SQLException{
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Country country = new Country(3750, "Great Country");
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('3750', 'Country Simple Name')");
        countryService.delete(3750);
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM country");
        rs.next();
        int count = rs.getInt("count");
        assertEquals(0, count);
    }

    @Test
    @Ignore
    public void deleteTestFalse() throws SQLException{
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Country country = new Country(3750, "Great Country");
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('3750', 'Country Simple Name')");
        countryService.delete(country.getId());
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM country");
        rs.next();
        int count = rs.getInt("count");
        assertNotEquals(1, count);
    }

    @Test
    @Ignore
    public void getCountryByIdTestTrue() throws SQLException {
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1850', 'Country Just Country')");
        Country country = countryService.getCountryById(1850);
        assertEquals("Country Just Country", country.getName());
    }

    @Test
    @Ignore
    public void getCountryByIdTestFalse() throws SQLException {
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1850', 'Country Just Country')");
        Country country = countryService.getCountryById(1850);
        assertNotEquals("", country.getName());
    }

    @Test
    @Ignore
    public void getAllCountriesTestTrue() throws SQLException {
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1851', 'Country First')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('18298', 'Country Second')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('6582', 'Country Third')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('458', 'Country Fourth')");
        List<Country> countries = countryService.getAllCountries();
        assertEquals(4, countries.size());
    }

    @Test
    @Ignore
    public void getAllCountriesTestFalse() throws SQLException {
        CountryService countryService = new CountryServiceImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        Connection c = dbCountry.getTestDatabase().getConnection();
        Statement stmt = c.createStatement();
        stmt.execute("INSERT INTO country (id, name) VALUES ('1851', 'Country First')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('18298', 'Country Second')");
        stmt.execute("INSERT INTO country (id, name) VALUES ('1888', 'Country Third')");
        List<Country> countries = countryService.getAllCountries();
        assertNotEquals(0, countries.size());
    }
}

class SimplePreparer implements DatabaseConnectionPreparer {
    private final String name;

    public SimplePreparer(String name) {
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

