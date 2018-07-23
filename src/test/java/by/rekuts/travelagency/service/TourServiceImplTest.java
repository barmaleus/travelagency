package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Tour;
import by.rekuts.travelagency.service.impl.TourServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class TourServiceImplTest {
    @Test
    public void insertTest(){
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        doNothing().when(tourService).insert(new Tour());
        tourService.insert(new Tour());
        tourService.insert(new Tour());
        verify(tourService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        doNothing().when(tourService).insert(new Tour());
        tourService.delete(6);
        tourService.delete(8);
        verify(tourService, times(2)).delete(anyInt());
    }

    @Test
    public void getReviewByIdTestTrue() {
        List<Tour> tours = Arrays.asList(
                new Tour(1, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 68, 91),
                new Tour(2, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 99, 16),
                new Tour(3, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 33, 14));
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getTourById(2)).thenReturn(tours.get(1));
        Tour tour = tourService.getTourById(2);
        assertEquals(99, tour.getHotelId());
    }

    @Test
    public void getReviewByIdTestFalse() {
        List<Tour> tours = Arrays.asList(
                new Tour(1, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 68, 91),
                new Tour(2, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 99, 16),
                new Tour(3, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 33, 14));
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getTourById(2)).thenReturn(tours.get(1));
        Tour tour = tourService.getTourById(2);
        assertNotEquals(68, tour.getHotelId());
    }

    @Test
    public void getAllReviewsTestTrue() {
        List<Tour> tours = Arrays.asList(
                new Tour(1, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 68, 91),
                new Tour(2, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 99, 16),
                new Tour(3, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 33, 14));
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getAllTours()).thenReturn(tours);
        List<Tour> tourList = tourService.getAllTours();
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllReviewsTestFalse() throws SQLException {
        List<Tour> tours = Arrays.asList(
                new Tour(1, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 68, 91),
                new Tour(2, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 99, 16),
                new Tour(3, "photo.jpg", LocalDate.now(), 7, "tour description", BigDecimal.valueOf(480.99), "cultural", 33, 14));
        TourServiceImpl tourService = mock(TourServiceImpl.class);
        when(tourService.getAllTours()).thenReturn(tours);
        List<Tour> tourList = tourService.getAllTours();
        assertNotEquals(0, tourList.size());
    }
}
