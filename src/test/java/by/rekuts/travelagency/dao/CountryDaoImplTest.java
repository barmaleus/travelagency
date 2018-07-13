package by.rekuts.travelagency.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import com.opentable.db.postgres.embedded.DatabaseConnectionPreparer;
import com.opentable.db.postgres.embedded.DatabasePreparer;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;

import by.rekuts.travelagency.TravelAgencyApplication;
import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.CountryService;

import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/embedded-db.properties"})
public class CountryDaoImplTest {

	@Autowired
	ApplicationContext context;
	
	private final DatabasePreparer prepCountry = new SimplePreparer("country");
	@Rule
	public PreparedDbRule dbCountry = EmbeddedPostgresRules.preparedDatabase(prepCountry);
		
    @Test
    public void testEmbeddedPgDb() throws Exception
    {
        try (EmbeddedPostgres pg = EmbeddedPostgres.start();
             Connection c = pg.getPostgresDatabase().getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT 1");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
            assertFalse(rs.next());
        }
    }
    
	@Test
	public void testDbs() throws Exception {
        try (Connection c = dbCountry.getTestDatabase().getConnection();
                Statement stmt = c.createStatement()) {
        	stmt.execute("INSERT INTO country (id, name) VALUES ('1', 'Belarus')");
            ResultSet rs = stmt.executeQuery("SELECT * FROM country");
            rs.next();
            assertEquals(1, rs.getInt("id"));
            assertEquals("Belarus", rs.getString("name"));
        }
    }
    
	
	
	
//    @Test //todo how to add application conttext?
//	public void testCountryDaoImplInsert() throws Exception {
//    	CountryService countryService = context.getBean(CountryService.class);
//        try (Connection c = dbCountry.getTestDatabase().getConnection();
//                Statement stmt = c.createStatement()) {
//        	stmt.execute("INSERT INTO country (id, name) VALUES ('1', 'Belarus')");
//        	Country belarus = new Country(375, "Belarus");
//
//        	Country countra = countryService.getCountryById(1);
//        	assertEquals(countra.getName(), belarus.getName());
//
////        	stmt.execute("INSERT INTO country (id, name) VALUES ('1', 'Belarus')");
////            ResultSet rs = stmt.executeQuery("SELECT * FROM country");
////            rs.next();
////            assertEquals(1, rs.getInt("id"));
////            assertEquals("Belarus", rs.getString("name"));
//        }
//    }

    static class SimplePreparer implements DatabaseConnectionPreparer {
        private final String name;

        public SimplePreparer(String name) {
            this.name = name;
        }

        @Override
        public void prepare(Connection conn) throws SQLException {
            try (PreparedStatement stmt = conn.prepareStatement(String.format(
                    "CREATE TABLE %s (id int, name text)", name))) {
                stmt.execute();
            }
        }
}
}
