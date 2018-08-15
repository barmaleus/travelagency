//package by.rekuts.travelagency.logic.service.integration;
//
//import by.rekuts.travelagency.logic.config.TestRepositoryConfig;
//import by.rekuts.travelagency.logic.repository.CountrySpecification;
//import by.rekuts.travelagency.logic.repository.HotelSpecification;
//import by.rekuts.travelagency.logic.repository.TourSpecification;
//import by.rekuts.travelagency.logic.domain.Tour;
//import by.rekuts.travelagency.logic.service.CountryService;
//import by.rekuts.travelagency.logic.service.HotelService;
//import by.rekuts.travelagency.logic.service.TourService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.PersistenceException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class TourServiceImplTest {
//    @Autowired
//    TourService tourService;
//    @Autowired
//    HotelService hotelService;
//    @Autowired
//    CountryService countryService;
//
//    private Tour tour = new Tour();
//
//    @Before
//    public void initializeToursList() {
//        tour.setPhoto("photo.jpg");
//        tour.setDate(LocalDate.now());
//        tour.setDuration(14);
//        tour.setDescription("Interesting tour1");
//        tour.setCost(BigDecimal.valueOf(900));
//        tour.setTourType(Tour.TourType.cultural);
//        tour.setHotel(hotelService.getList(new HotelSpecification(68)).get(0));
//        tour.setCountry(countryService.getList(new CountrySpecification(91)).get(0));
//    }
//
//    @Test
//    public void insertTest(){
//        int countToursFirst = tourService.getList(new TourSpecification()).size();
//        tourService.insert(tour);
//        int countToursLast = tourService.getList(new TourSpecification()).size();
//        assertEquals(1, countToursLast - countToursFirst);
//    }
//
//    @Test (expected = PersistenceException.class)
//    public void insertTestFalse(){
//        tour.setId(1);
//        tourService.insert(tour);
//    }
//
//    @Test
//    public void deleteTest() {
//        int countToursFirst = tourService.getList(new TourSpecification()).size();
//        tourService.delete(1);
//        int countToursLast = tourService.getList(new TourSpecification()).size();
//        assertEquals(1, countToursFirst - countToursLast);
//    }
//
//    @Test
//    public void getTourByIdTestTrue() {
//        Tour tour = tourService.getList(new TourSpecification(4)).get(0);
//        assertEquals("Nam dui.", tour.getDescription());
//    }
//
//    @Test (expected = IndexOutOfBoundsException.class)
//    public void getTourByIdTestFalse1() {
//        tourService.getList(new TourSpecification(1001)).get(0);
//    }
//
//    @Test
//    public void getTourByIdTestFalse2() {
//        List<Tour> tours = tourService.getList(new TourSpecification(1001));
//        assertTrue(tours.isEmpty());
//    }
//
//    @Test
//    public void getAllToursTest() {
//        List<Tour> tourList = tourService.getList(new TourSpecification());
//        assertEquals(1000, tourList.size());
//    }
//
//    @Test
//    public void getToursByCriteria() {
//        TourSpecification specification = new TourSpecification();
//        specification.setTourType(Tour.TourType.safari);
//        List<Tour> result = tourService.getList(specification);
//        assertEquals(58, result.size());
//    }
//
//    @Test
//    public void getToursByUserId() {
//        TourSpecification specification = new TourSpecification();
//        specification.setUserId(101);
//        List<Tour> result = tourService.getList(specification);
//        assertEquals(2, result.size());
//    }
//}
