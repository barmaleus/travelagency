package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.domain.Hotel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
@Transactional
public class HotelDaoImplTest {

    @Autowired
    HotelDao hotelDao;

    @Test
    public void insertTest() {
        Hotel hotel = new Hotel();
        hotel.setName("Tourist");
        hotel.setWebsite("tourist.by");
        hotel.setStars(4);
        hotel.setLatitude(BigDecimal.valueOf(44.5468));
        hotel.setLongitude(BigDecimal.valueOf(-78.64));
        hotel.setFeatures(new ArrayList<>(Arrays.asList(Hotel.Features.A.getValue(), Hotel.Features.C.getValue())));
        int countFirst = hotelDao.getAllHotels().size();
        hotelDao.insert(hotel);
        int countLast = hotelDao.getAllHotels().size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteHotelWithRefferenceTestFalse() {
        int countFirst = hotelDao.getAllHotels().size();
        hotelDao.delete(100);
        int countLast = hotelDao.getAllHotels().size();
        Assert.assertEquals(0, countFirst - countLast);
    }

    @Test
    public void deleteHotelTestTrue() {
        int countFirst = hotelDao.getAllHotels().size();
        hotelDao.delete(101);
        int countLast = hotelDao.getAllHotels().size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getHotelByIdTestTrue() {
        Hotel hotel = hotelDao.getHotelById(1);
        Assert.assertEquals("Etiam Imperdiet Corporation", hotel.getName());
    }

    @Test
    public void getAllHotelsTestTrue() {
        List<Hotel> hotels = hotelDao.getAllHotels();
        Assert.assertEquals(100, hotels.size());
    }
}

