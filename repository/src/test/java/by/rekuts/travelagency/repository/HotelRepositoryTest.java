package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class HotelRepositoryTest {

    @Autowired
    HotelRepository hotelRepository;

    @Test
    public void insertTest() {
        var hotel = new Hotel();
        hotel.setName("Tourist");
        hotel.setWebsite("tourist.by");
        hotel.setStars(4);
        hotel.setLatitude(BigDecimal.valueOf(44.5468));
        hotel.setLongitude(BigDecimal.valueOf(-78.64));
        hotel.setFeatures(new ArrayList<>(Arrays.asList(Hotel.Features.A.getValue(), Hotel.Features.C.getValue())));
        var countFirst = hotelRepository.getList(new HotelSpecification()).size();
        hotelRepository.insert(hotel);
        var countLast = hotelRepository.getList(new HotelSpecification()).size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteHotelWithRefferenceTestFalse() {
        var countFirst = hotelRepository.getList(new HotelSpecification()).size();
        hotelRepository.delete(100);
        var countLast = hotelRepository.getList(new HotelSpecification()).size();
        Assert.assertEquals(0, countFirst - countLast);
    }

    @Test
    public void deleteHotelTestTrue() {
        var countFirst = hotelRepository.getList(new HotelSpecification()).size();
        hotelRepository.delete(101);
        var countLast = hotelRepository.getList(new HotelSpecification()).size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getHotelByIdTestTrue() {
        var hotel = hotelRepository.getList(new HotelSpecification(1)).get(0);
        Assert.assertEquals("Etiam Imperdiet Corporation", hotel.getName());
    }

    @Test
    public void getAllHotelsTestTrue() {
        var hotels = hotelRepository.getList(new HotelSpecification());
        Assert.assertEquals(101, hotels.size());
    }
}

