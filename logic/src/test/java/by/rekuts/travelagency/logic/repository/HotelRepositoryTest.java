//package by.rekuts.travelagency.logic.repository;
//
//import by.rekuts.travelagency.logic.config.TestRepositoryConfig;
//import by.rekuts.travelagency.logic.domain.Hotel;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class HotelRepositoryTest {
//
//    @Autowired
//    HotelRepository hotelRepository;
//
//    @Test
//    public void insertTest() {
//        Hotel hotel = new Hotel();
//        hotel.setName("Tourist");
//        hotel.setWebsite("tourist.by");
//        hotel.setStars(4);
//        hotel.setLatitude(BigDecimal.valueOf(44.5468));
//        hotel.setLongitude(BigDecimal.valueOf(-78.64));
//        hotel.setFeatures(new ArrayList<>(Arrays.asList(Hotel.Features.A.getValue(), Hotel.Features.C.getValue())));
//        int countFirst = hotelRepository.getList(new HotelSpecification()).size();
//        hotelRepository.insert(hotel);
//        int countLast = hotelRepository.getList(new HotelSpecification()).size();
//        Assert.assertEquals(1, countLast - countFirst);
//    }
//
//    @Test
//    public void deleteHotelWithRefferenceTestFalse() {
//        int countFirst = hotelRepository.getList(new HotelSpecification()).size();
//        hotelRepository.delete(100);
//        int countLast = hotelRepository.getList(new HotelSpecification()).size();
//        Assert.assertEquals(0, countFirst - countLast);
//    }
//
//    @Test
//    public void deleteHotelTestTrue() {
//        int countFirst = hotelRepository.getList(new HotelSpecification()).size();
//        hotelRepository.delete(101);
//        int countLast = hotelRepository.getList(new HotelSpecification()).size();
//        Assert.assertEquals(1, countFirst - countLast);
//    }
//
//    @Test
//    public void getHotelByIdTestTrue() {
//        Hotel hotel = hotelRepository.getList(new HotelSpecification(1)).get(0);
//        Assert.assertEquals("Etiam Imperdiet Corporation", hotel.getName());
//    }
//
//    @Test
//    public void getAllHotelsTestTrue() {
//        List<Hotel> hotels = hotelRepository.getList(new HotelSpecification());
//        Assert.assertEquals(101, hotels.size());
//    }
//}
//
