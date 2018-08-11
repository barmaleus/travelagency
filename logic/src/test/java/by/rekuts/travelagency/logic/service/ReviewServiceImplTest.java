package by.rekuts.travelagency.logic.service;

import by.rekuts.travelagency.logic.config.TestRepositoryConfig;
import by.rekuts.travelagency.logic.domain.Review;
import by.rekuts.travelagency.logic.domain.Tour;
import by.rekuts.travelagency.logic.domain.User;
import by.rekuts.travelagency.logic.repository.ReviewSpecification;
import by.rekuts.travelagency.logic.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
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
public class ReviewServiceImplTest {
    private List<Review> reviews;

    @Before
    public void initializeReviewsList() {
        User user1 = new User();
        user1.setId(5);
        Tour tour1 = new Tour();
        tour1.setId(67);
        Review review1 = new Review();
        review1.setId(1);
        review1.setDate(LocalDateTime.now());
        review1.setText("review text1");
        review1.setUser(user1);
        review1.setTour(tour1);
        User user2 = new User();
        user2.setId(4);
        Tour tour2 = new Tour();
        tour2.setId(86);
        Review review2 = new Review();
        review2.setId(2);
        review2.setDate(LocalDateTime.now());
        review2.setText("review text2");
        review2.setUser(user2);
        review2.setTour(tour2);
        User user3 = new User();
        user3.setId(9);
        Tour tour3 = new Tour();
        tour3.setId(3);
        Review review3 = new Review();
        review3.setId(3);
        review3.setDate(LocalDateTime.now());
        review3.setText("review text3");
        review3.setUser(user3);
        review3.setTour(tour3);
        reviews = Arrays.asList(review1, review2, review3);
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
        ReviewSpecification specification = new ReviewSpecification(2);
        List<Review> singletonList = Collections.singletonList(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        Review review = reviewService.getList(specification).get(0);
        assertEquals("review text2", review.getText());
    }

    @Test
    public void getReviewByIdTestFalse() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        ReviewSpecification specification = new ReviewSpecification(2);
        List<Review> singletonList = Collections.singletonList(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        Review review = reviewService.getList(specification).get(0);
        assertNotEquals("review text1", review.getText());
    }

    @Test
    public void getAllReviewsTestTrue() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        ReviewSpecification specification = new ReviewSpecification();
        when(reviewService.getList(specification)).thenReturn(reviews);
        List<Review> reviewList = reviewService.getList(specification);
        assertEquals(3, reviewList.size());
    }

    @Test
    public void getAllReviewsTestFalse() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        ReviewSpecification specification = new ReviewSpecification();
        when(reviewService.getList(specification)).thenReturn(reviews);
        List<Review> reviewList = reviewService.getList(specification);
        assertNotEquals(0, reviewList.size());
    }

    @Test
    public void getReviewsByUserIdTest() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        ReviewSpecification specification = new ReviewSpecification();
        specification.setUserId(4);
        List<Review> singletonList = Collections.singletonList(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        List<Review> reviews = reviewService.getList(specification);
        assertEquals("review text2", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTest() {
        ReviewServiceImpl reviewService = mock(ReviewServiceImpl.class);
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(86);
        List<Review> singletonList = Collections.singletonList(reviews.get(1));
        when(reviewService.getList(specification)).thenReturn(singletonList);
        List<Review> reviews = reviewService.getList(specification);
        assertEquals("review text2", reviews.get(0).getText());
    }
}
