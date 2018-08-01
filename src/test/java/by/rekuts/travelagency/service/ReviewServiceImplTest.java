package by.rekuts.travelagency.service;

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
        reviews = Arrays.asList(
                new Review(1, LocalDateTime.now(), "review text", 5, 67),
                new Review(2, LocalDateTime.now(), "review text", 4, 86),
                new Review(3, LocalDateTime.now(), "review text", 9, 3));
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
        assertEquals(86, review.getTourId());
    }

    @Test
    public void getReviewByIdTestFalse() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        when(reviewService.getReviewById(2)).thenReturn(reviews.get(1));
        Review review = reviewService.getReviewById(2);
        assertNotEquals(67, review.getTourId());
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
}
