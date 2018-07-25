package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Tour;
import by.rekuts.travelagency.service.impl.TourServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
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
        Tour tour1 = Tour.builder()
                .hotelId(1)
                .photo("photo.jpg")
                .date(LocalDate.now())
                .duration(14)
                .description("Interesting tour")
                .cost(BigDecimal.valueOf(900))
                .tourType(Tour.TourType.J.getValue())
                .hotelId(68)
                .countryId(91)
                .build();
        Tour tour2 = Tour.builder()
                .hotelId(2)
                .photo("photo.jpg")
                .date(LocalDate.now())
                .duration(14)
                .description("Interesting tour")
                .cost(BigDecimal.valueOf(900))
                .tourType(Tour.TourType.J.getValue())
                .hotelId(99)
                .countryId(16)
                .build();
        Tour tour3 = Tour.builder()
                .hotelId(3)
                .photo("photo.jpg")
                .date(LocalDate.now())
                .duration(14)
                .description("Interesting tour")
                .cost(BigDecimal.valueOf(900))
                .tourType(Tour.TourType.J.getValue())
                .hotelId(33)
                .countryId(14)
                .build();
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
        assertEquals(99, tour.getHotelId());
    }

    @Test
    public void getReviewByIdTestFalse() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getTourById(2)).thenReturn(tours.get(1));
        Tour tour = tourService.getTourById(2);
        assertNotEquals(68, tour.getHotelId());
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
}
