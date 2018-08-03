package by.rekuts.travelagency.service;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.service.impl.TourServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class TourServiceImplTest {
    private static List<Tour> tours;

    @Before
    public void initializeToursList() {
        HotelDao hotelDao = mock(HotelDao.class);
        CountryDao countryDao = mock(CountryDao.class);
        Tour tour1 = new Tour();
        tour1.setId(1);
        tour1.setPhoto("photo.jpg");
        tour1.setDate(LocalDate.now());
        tour1.setDuration(14);
        tour1.setDescription("Interesting tour1");
        tour1.setCost(BigDecimal.valueOf(900));
        tour1.setTourType(Tour.TourType.cultural);
        tour1.setHotel(hotelDao.getHotelById(68));
        tour1.setCountry(countryDao.getCountryById(91));
        Tour tour2 = new Tour();
        tour2.setId(2);
        tour2.setPhoto("photo.jpg");
        tour2.setDate(LocalDate.now());
        tour2.setDuration(14);
        tour2.setDescription("Interesting tour2");
        tour2.setCost(BigDecimal.valueOf(900));
        tour2.setTourType(Tour.TourType.safari);
        tour2.setHotel(hotelDao.getHotelById(99));
        tour2.setCountry(countryDao.getCountryById(16));
        Tour tour3 = new Tour();
        tour3.setId(3);
        tour3.setPhoto("photo.jpg");
        tour3.setDate(LocalDate.now());
        tour3.setDuration(14);
        tour3.setDescription("Interesting tour3");
        tour3.setCost(BigDecimal.valueOf(900));
        tour3.setTourType(Tour.TourType.safari);
        tour3.setHotel(hotelDao.getHotelById(33));
        tour3.setCountry(countryDao.getCountryById(14));
        tours = Arrays.asList(tour1, tour2, tour3);
    }

    @Test
    public void insertTest(){
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        doNothing().when(tourService).insert(any(Tour.class));
        tourService.insert(any(Tour.class));
        tourService.insert(any(Tour.class));
        verify(tourService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        doNothing().when(tourService).insert(any(Tour.class));
        tourService.delete(6);
        tourService.delete(8);
        verify(tourService, times(2)).delete(anyInt());
    }

    @Test
    public void getReviewByIdTestTrue() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getTourById(2)).thenReturn(tours.get(1));
        Tour tour = tourService.getTourById(2);
        assertEquals("Interesting tour2", tour.getDescription());
    }

    @Test
    public void getReviewByIdTestFalse() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getTourById(2)).thenReturn(tours.get(1));
        Tour tour = tourService.getTourById(2);
        assertNotEquals("Interesting tour1", tour.getDescription());
    }

    @Test
    public void getAllReviewsTestTrue() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getAllTours()).thenReturn(tours);
        List<Tour> tourList = tourService.getAllTours();
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllReviewsTestFalse() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getAllTours()).thenReturn(tours);
        List<Tour> tourList = tourService.getAllTours();
        assertNotEquals(0, tourList.size());
    }

    @Test
    public void getToursByCriteria() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getToursByCriteria(null, null, null, Tour.TourType.safari,
                null, null, null)).thenReturn(Arrays.asList(tours.get(1), tours.get(2)));
        List<Tour> result = tourService.getToursByCriteria(null, null, null,
                Tour.TourType.safari, null, null, null);
        assertEquals(2, result.size());
    }

    @Test
    public void getToursByUserId() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getToursByUserId(2)).thenReturn(tours);
        List<Tour> result = tourService.getToursByUserId(2);
        assertEquals(3, result.size());
    }
}
