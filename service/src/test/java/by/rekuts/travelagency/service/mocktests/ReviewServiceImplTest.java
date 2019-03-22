package by.rekuts.travelagency.service.mocktests;

import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.ReviewSpecification;
import by.rekuts.travelagency.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
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
        var user1 = new User();
        user1.setId(5);
        var tour1 = new Tour();
        tour1.setId(67);
        var review1 = new Review();
        review1.setId(1);
        review1.setDate(LocalDateTime.now());
        review1.setText("review text1");
        review1.setUser(user1);
        review1.setTour(tour1);
        var user2 = new User();
        user2.setId(4);
        var tour2 = new Tour();
        tour2.setId(86);
        var review2 = new Review();
        review2.setId(2);
        review2.setDate(LocalDateTime.now());
        review2.setText("review text2");
        review2.setUser(user2);
        review2.setTour(tour2);
        var user3 = new User();
        user3.setId(9);
        var tour3 = new Tour();
        tour3.setId(3);
        var review3 = new Review();
        review3.setId(3);
        review3.setDate(LocalDateTime.now());
        review3.setText("review text3");
        review3.setUser(user3);
        review3.setTour(tour3);
        reviews = List.of(review1, review2, review3);
    }

    @Test
    public void insertTest(){
        var reviewService = mock(ReviewServiceImpl.class);
        doNothing().when(reviewService).insert(any(Review.class));
        reviewService.insert(new Review());
        reviewService.insert(new Review());
        verify(reviewService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        var reviewService = mock(ReviewServiceImpl.class);
        doNothing().when(reviewService).insert(any(Review.class));
        reviewService.delete(6);
        reviewService.delete(8);
        verify(reviewService, times(2)).delete(anyInt());
    }

    @Test
    public void getReviewByIdTestTrue() {
        var reviewService = mock(ReviewServiceImpl.class);
        var specification = new ReviewSpecification(2);
        var singletonList = List.of(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        var review = reviewService.getList(specification).get(0);
        assertEquals("review text2", review.getText());
    }

    @Test
    public void getReviewByIdTestFalse() {
        var reviewService = mock(ReviewServiceImpl.class);
        var specification = new ReviewSpecification(2);
        var singletonList = List.of(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        var review = reviewService.getList(specification).get(0);
        assertNotEquals("review text1", review.getText());
    }

    @Test
    public void getAllReviewsTestTrue() {
        var reviewService = mock(ReviewServiceImpl.class);
        var specification = new ReviewSpecification();
        when(reviewService.getList(specification)).thenReturn(reviews);
        var reviewList = reviewService.getList(specification);
        assertEquals(3, reviewList.size());
    }

    @Test
    public void getAllReviewsTestFalse() {
        var reviewService = mock(ReviewServiceImpl.class);
        var specification = new ReviewSpecification();
        when(reviewService.getList(specification)).thenReturn(reviews);
        var reviewList = reviewService.getList(specification);
        assertNotEquals(0, reviewList.size());
    }

    @Test
    public void getReviewsByUserIdTest() {
        var reviewService = mock(ReviewServiceImpl.class);
        var specification = new ReviewSpecification();
        specification.setUserId(4);
        var singletonList = List.of(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        var reviews = reviewService.getList(specification);
        assertEquals("review text2", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTest() {
        var reviewService = mock(ReviewServiceImpl.class);
        var specification = new ReviewSpecification();
        specification.setTourId(86);
        var singletonList = List.of(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        var reviews = reviewService.getList(specification);
        assertEquals("review text2", reviews.get(0).getText());
    }
}
