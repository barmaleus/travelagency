package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.service.HotelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class HotelSerivceImplTest {
    @Autowired
    HotelService hotelService;

    private Hotel hotel = new Hotel();

    @Before
    public void initializeHotel() {
        hotel.setName("Tourist");
        hotel.setStars(3);
        hotel.setWebsite("tourist.by");
        hotel.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel.setFeatures(new ArrayList<>());
    }

    @Test
    public void insertHotelTest(){
        int countHotelsFirst = hotelService.getAllHotels().size();
        hotelService.insert(hotel);
        int countHotelsLast = hotelService.getAllHotels().size();
        assertEquals(1, countHotelsLast - countHotelsFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertNullHotelTest(){
        hotel.setName(null);
        hotelService.insert(hotel);
    }

    @Test
    public void deleteHotelTestTrue(){
        int countHotelsFirst = hotelService.getAllHotels().size();
        hotelService.delete(101);
        int countHotelsLast = hotelService.getAllHotels().size();
        assertEquals(1, countHotelsFirst - countHotelsLast);
    }

    @Test
    public void deleteHotelTestFalseForeignKeys(){
        int countHotelsFirst = hotelService.getAllHotels().size();
        hotelService.delete(1);
        int countHotelsLast = hotelService.getAllHotels().size();
        assertEquals(0, countHotelsFirst - countHotelsLast);
    }

    @Test
    public void getHotelByIdTrue(){
        Hotel gettedHotel = hotelService.getHotelById(101);

        assertEquals("Tourist", gettedHotel.getName());
    }

    @Test
    public void getHotelByIdFalse(){
        Hotel gettedHotel = hotelService.getHotelById(102);
        assertEquals(null, gettedHotel);
    }

    @Test
    public void getAllHotelsTestTrue() {
        List<Hotel> hotels = hotelService.getAllHotels();
        Assert.assertEquals(101, hotels.size());
    }
}
