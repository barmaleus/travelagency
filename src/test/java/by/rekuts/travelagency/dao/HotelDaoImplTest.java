package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.impl.HotelDaoImpl;
import by.rekuts.travelagency.dao.subjects.Hotel;
import com.opentable.db.postgres.embedded.DatabaseConnectionPreparer;
import com.opentable.db.postgres.embedded.DatabasePreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HotelDaoImplTest {
    private final DatabasePreparer prepCountry = new SimpleHotelPreparer("hotel");
    @Rule
    public PreparedDbRule dbCountry = EmbeddedPostgresRules.preparedDatabase(prepCountry);

    @Test
    public void insertTest() throws SQLException {
        HotelDao hotelDao = new HotelDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        String[] array = new String[2];
        array[0] = Hotel.Features.C.getValue();
        array[1] = Hotel.Features.E.getValue();
        Connection con = dbCountry.getTestDatabase().getConnection();
        Array sqlArray = con.createArrayOf("features", array);
        Hotel hotel = new Hotel(8451, "Hotel Name", 5, "hotel-domain.com",new BigDecimal(-148.745164), new BigDecimal(98.4568214), sqlArray);
        hotelDao.insert(hotel);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM hotel");
        rs.next();
        String hotelName = rs.getString("name");
        assertEquals(hotel.getName(), hotelName);
    }

    @Test
    public void deleteTestTrue() throws SQLException{
        HotelDao hotelDao = new HotelDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        String[] array = new String[2];
        array[0] = Hotel.Features.C.getValue();
        array[1] = Hotel.Features.E.getValue();
        Connection con = dbCountry.getTestDatabase().getConnection();
        Array sqlArray = con.createArrayOf("features", array);
        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO hotel (id, name, stars, website, latitude, longitude, features) " +
                        "VALUES ('3750', 'Hotel Simple Name', 4, 'site.by', 142.565482, 86.217845, ?)");
        pstmt.setArray(1, sqlArray);
        pstmt.executeUpdate();
        hotelDao.delete(3750);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM hotel");
        rs.next();
        int count = rs.getInt("count");
        assertEquals(0, count);
    }

    @Test
    public void getCountryByIdTestTrue() throws SQLException {
        HotelDao hotelDao = new HotelDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        String[] array = new String[2];
        array[0] = Hotel.Features.C.getValue();
        array[1] = Hotel.Features.E.getValue();
        Connection con = dbCountry.getTestDatabase().getConnection();
        Array sqlArray = con.createArrayOf("features", array);
        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO hotel (id, name, stars, website, latitude, longitude, features) " +
                        "VALUES ('3750', 'Hotel Simple Name', 4, 'site.by', 142.565482, 86.217845, ?)");
        pstmt.setArray(1, sqlArray);
        pstmt.executeUpdate();
        Hotel hotel = hotelDao.getHotelById(3750);
        assertEquals("Hotel Simple Name", hotel.getName());
    }

    @Test
    public void getAllCountriesTestTrue() throws SQLException {
        HotelDao hotelDao = new HotelDaoImpl(new JdbcTemplate(dbCountry.getTestDatabase()));
        String[] array = new String[2];
        array[0] = Hotel.Features.C.getValue();
        array[1] = Hotel.Features.E.getValue();
        Connection con = dbCountry.getTestDatabase().getConnection();
        Array sqlArray = con.createArrayOf("features", array);
        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO hotel (id, name, stars, website, latitude, longitude, features) " +
                        "VALUES ('3750', 'Hotel Simple Name', 4, 'site.by', 142.565482, 86.217845, ?)");
        pstmt.setArray(1, sqlArray);
        pstmt.executeUpdate();
        PreparedStatement pstmt1 = con.prepareStatement(
                "INSERT INTO hotel (id, name, stars, website, latitude, longitude, features) " +
                        "VALUES ('84521', 'Hotel Simple Number Two', 4, 'site2.by', 142.56582, 84.217845, ?)");
        pstmt1.setArray(1, sqlArray);
        pstmt1.executeUpdate();
        List<Hotel> hotels = hotelDao.getAllHotels();
        assertEquals(1, hotels.size());
    }
}

class SimpleHotelPreparer implements DatabaseConnectionPreparer {
    private final String name;

    SimpleHotelPreparer(String name) {
        this.name = name;
    }

    @Override
    public void prepare(Connection conn) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(String.format(
                "CREATE TYPE features AS ENUM (\n" +
                        "    'swimming pool',\n" +
                        "    'all inclusive',\n" +
                        "    'fresh towel',\n" +
                        "    'carpets on the walls',\n" +
                        "    'free wifi',\n" +
                        "    'close to sea',\n" +
                        "    'free animators',\n" +
                        "    'free beer',\n" +
                        "    'air conditioning',\n" +
                        "    'children room'\n" +
                        "); " +
                        "CREATE TABLE %s (id int NOT NULL, name text NOT NULL, stars smallint, website text, " +
                        "latitude numeric, longitude numeric, features features[], PRIMARY KEY (id))", name))) {
            stmt.execute();
        }
    }
}

