package by.rekuts.travelagency.service.mocktests;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.impl.TourServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class TourServiceImplTest {
    private static List<Tour> tours;

    @Before
    public void initializeToursList() {
        var hotel1 = new Hotel();
        hotel1.setId(68);
        var country1 = new Country();
        country1.setId(91);
        var tour1 = new Tour();
        tour1.setId(1);
        tour1.setPhoto("photo.jpg");
        tour1.setDate(LocalDate.now());
        tour1.setDuration(14);
        tour1.setDescription("Interesting tour1");
        tour1.setCost(BigDecimal.valueOf(900));
        tour1.setTourType(Tour.TourType.cultural);
        tour1.setHotel(hotel1);
        tour1.setCountry(country1);
        var hotel2 = new Hotel();
        hotel2.setId(99);
        var country2 = new Country();
        country2.setId(16);
        var tour2 = new Tour();
        tour2.setId(2);
        tour2.setPhoto("photo.jpg");
        tour2.setDate(LocalDate.now());
        tour2.setDuration(14);
        tour2.setDescription("Interesting tour2");
        tour2.setCost(BigDecimal.valueOf(900));
        tour2.setTourType(Tour.TourType.safari);
        tour2.setHotel(hotel2);
        tour2.setCountry(country2);
        var hotel3 = new Hotel();
        hotel3.setId(33);
        var country3 = new Country();
        country3.setId(14);
        var tour3 = new Tour();
        tour3.setId(3);
        tour3.setPhoto("photo.jpg");
        tour3.setDate(LocalDate.now());
        tour3.setDuration(14);
        tour3.setDescription("Interesting tour3");
        tour3.setCost(BigDecimal.valueOf(900));
        tour3.setTourType(Tour.TourType.safari);
        tour3.setHotel(hotel3);
        tour1.setCountry(country3);
        tours = List.of(tour1, tour2, tour3);
    }

    @Test
    public void insertTest(){
        var tourService = mock(TourServiceImpl.class);
        doNothing().when(tourService).insert(any(Tour.class));
        tourService.insert(any(Tour.class));
        tourService.insert(any(Tour.class));
        verify(tourService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        var tourService = mock(TourServiceImpl.class);
        doNothing().when(tourService).insert(any(Tour.class));
        tourService.delete(6);
        tourService.delete(8);
        verify(tourService, times(2)).delete(anyInt());
    }

    @Test
    public void getTourByIdTestTrue() {
        var tourService = mock(TourServiceImpl.class);
        var specification = new TourSpecification(2);
        var singletonList = List.of(tours.get(1));
        when(tourService.getList(specification)).thenReturn(singletonList);
        var tour = tourService.getList(specification).get(0);
        assertEquals("Interesting tour2", tour.getDescription());
    }

    @Test
    public void getTourByIdTestFalse() {
        var tourService = mock(TourServiceImpl.class);
        var specification = new TourSpecification(2);
        var singletonList = List.of(tours.get(1));
        when(tourService.getList(specification)).thenReturn(singletonList);
        var tour = tourService.getList(specification).get(0);
        assertNotEquals("Interesting tour1", tour.getDescription());
    }

    @Test
    public void getAllToursTestTrue() {
        var tourService = mock(TourServiceImpl.class);
        var specification = new TourSpecification();
        when(tourService.getList(specification)).thenReturn(tours);
        var tourList = tourService.getList(specification);
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllToursTestFalse() {
        var tourService = mock(TourServiceImpl.class);
        var specification = new TourSpecification();
        when(tourService.getList(specification)).thenReturn(tours);
        var tourList = tourService.getList(specification);
        assertNotEquals(0, tourList.size());
    }

//    @Test
//    public void getToursByCriteria() {
//        TourServiceImpl tourService = mock(TourServiceImpl.class);
//        when(tourService.getToursByCriteria(null, null, null, Tour.TourType.safari,
//                null, null, null)).thenReturn(Arrays.asList(tours.get(1), tours.get(2)));
//        List<Tour> result = tourService.getToursByCriteria(null, null, null,
//                Tour.TourType.safari, null, null, null);
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void getToursByUserId() {
//        TourServiceImpl tourService = mock(TourServiceImpl.class);
//        when(tourService.getToursByUserId(2)).thenReturn(tours);
//        List<Tour> result = tourService.getToursByUserId(2);
//        assertEquals(3, result.size());
//    }
}
