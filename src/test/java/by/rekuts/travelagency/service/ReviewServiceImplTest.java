package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.UserDao;
import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ReviewServiceImplTest {
    private List<Review> reviews;

    @Before
    public void initializeReviewsList() {
        UserDao userDao = mock(UserDao.class);
        TourDao tourDao = mock(TourDao.class);
        reviews = Arrays.asList(
                new Review(1, LocalDateTime.now(), "review text1", userDao.getUserById(5), tourDao.getTourById(67)),
                new Review(2, LocalDateTime.now(), "review text2", userDao.getUserById(4), tourDao.getTourById(86)),
                new Review(3, LocalDateTime.now(), "review text3", userDao.getUserById(9), tourDao.getTourById(3)));
    }

    @Test
    public void insertTest(){
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        doNothing().when(reviewService).insert(any(Review.class));
        reviewService.insert(new Review());
        reviewService.insert(new Review());
        verify(reviewService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        doNothing().when(reviewService).insert(any(Review.class));
        reviewService.delete(6);
        reviewService.delete(8);
        verify(reviewService, times(2)).delete(anyInt());
    }

    @Test
    public void getReviewByIdTestTrue() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getReviewById(2)).thenReturn(reviews.get(1));
        Review review = reviewService.getReviewById(2);
        assertEquals("review text2", review.getText());
    }

    @Test
    public void getReviewByIdTestFalse() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getReviewById(2)).thenReturn(reviews.get(1));
        Review review = reviewService.getReviewById(2);
        assertNotEquals("review text1", review.getText());
    }

    @Test
    public void getAllReviewsTestTrue() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getAllReviews()).thenReturn(reviews);
        List<Review> reviewList = reviewService.getAllReviews();
        assertEquals(3, reviewList.size());
    }

    @Test
    public void getAllReviewsTestFalse() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getAllReviews()).thenReturn(reviews);
        List<Review> reviewList = reviewService.getAllReviews();
        assertNotEquals(0, reviewList.size());
    }

    @Test
    public void getReviewsByUserIdTest() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getReviewsByUserId(4)).thenReturn(Arrays.asList(reviews.get(1)));
        List<Review> reviews = reviewService.getReviewsByUserId(4);
        assertEquals("review text2", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTest() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getReviewsByTourId(86)).thenReturn(Arrays.asList(reviews.get(1)));
        List<Review> reviews = reviewService.getReviewsByTourId(86);
        assertEquals("review text2", reviews.get(0).getText());
    }
}
