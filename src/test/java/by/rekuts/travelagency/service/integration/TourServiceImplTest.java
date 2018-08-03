package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.service.CountryService;
import by.rekuts.travelagency.service.HotelService;
import by.rekuts.travelagency.service.TourService;
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
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class TourServiceImplTest {
    @Autowired
    TourService tourService;
    @Autowired
    HotelService hotelService;
    @Autowired
    CountryService countryService;

    private Tour tour = new Tour();

    @Before
    public void initializeToursList() {
        tour.setPhoto("photo.jpg");
        tour.setDate(LocalDate.now());
        tour.setDuration(14);
        tour.setDescription("Interesting tour1");
        tour.setCost(BigDecimal.valueOf(900));
        tour.setTourType(Tour.TourType.cultural);
        tour.setHotel(hotelService.getHotelById(68));
        tour.setCountry(countryService.getCountryById(91));
    }

    @Test
    public void insertTest(){
        int countToursFirst = tourService.getAllTours().size();
        tourService.insert(tour);
        int countToursLast = tourService.getAllTours().size();
        assertEquals(1, countToursLast - countToursFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertTestFalse(){
        tour.setId(1);
        tourService.insert(tour);
    }

    @Test
    public void deleteTest() {
        int countToursFirst = tourService.getAllTours().size();
        tourService.delete(1);
        int countToursLast = tourService.getAllTours().size();
        assertEquals(1, countToursFirst - countToursLast);
    }

    @Test
    public void getReviewByIdTestTrue() {
        Tour tour = tourService.getTourById(4);
        assertEquals("Nam dui.", tour.getDescription());
    }

    @Test
    public void getReviewByIdTestFalse() {
        Tour tour = tourService.getTourById(1001);
        assertEquals(null, tour);
    }

    @Test
    public void getAllReviewsTest() {
        List<Tour> tourList = tourService.getAllTours();
        assertEquals(1000, tourList.size());
    }

    @Test
    public void getToursByCriteria() {
        List<Tour> result = tourService.getToursByCriteria(null, null, null,
                Tour.TourType.safari, null, null, null);
        assertEquals(58, result.size());
    }

    @Test
    public void getToursByUserId() {
        List<Tour> result = tourService.getToursByUserId(101);
        assertEquals(2, result.size());
    }
}
