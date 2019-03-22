package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
import by.rekuts.travelagency.repository.HotelSpecification;
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
import static org.junit.Assert.assertTrue;

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
        var countHotelsFirst = hotelService.getList(new HotelSpecification()).size();
        hotelService.insert(hotel);
        var countHotelsLast = hotelService.getList(new HotelSpecification()).size();
        assertEquals(1, countHotelsLast - countHotelsFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertNullHotelTest(){
        hotel.setName(null);
        hotelService.insert(hotel);
    }

    @Test
    public void deleteHotelTestTrue(){
        var countHotelsFirst = hotelService.getList(new HotelSpecification()).size();
        hotelService.delete(101);
        var countHotelsLast = hotelService.getList(new HotelSpecification()).size();
        assertEquals(1, countHotelsFirst - countHotelsLast);
    }

    @Test
    public void deleteHotelTestFalseForeignKeys(){
        var countHotelsFirst = hotelService.getList(new HotelSpecification()).size();
        hotelService.delete(1);
        var countHotelsLast = hotelService.getList(new HotelSpecification()).size();
        assertEquals(0, countHotelsFirst - countHotelsLast);
    }

    @Test
    public void getHotelByIdTrue(){
        var gettedHotel = hotelService.getList(new HotelSpecification(101)).get(0);

        assertEquals("Tourist", gettedHotel.getName());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getHotelByIdFalse1(){
        hotelService.getList(new HotelSpecification(102)).get(0);
    }

    @Test
    public void getHotelByIdFalse2(){
        var hotels = hotelService.getList(new HotelSpecification(102));
        assertTrue(hotels.isEmpty());
    }

    @Test
    public void getAllHotelsTestTrue() {
        var hotels = hotelService.getList(new HotelSpecification());
        Assert.assertEquals(101, hotels.size());
    }
}
