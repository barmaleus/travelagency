package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.service.ReviewService;
import by.rekuts.travelagency.service.TourService;
import by.rekuts.travelagency.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class ReviewServiceImplTest {
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;
    @Autowired
    TourService tourService;

    private Review review = new Review();

    @Before
    public void initializeReview() {
        review.setDate(LocalDateTime.now());
        review.setText("Interesting tour");
        review.setUser(userService.getUserById(5));
        review.setTour(tourService.getTourById(47));
    }

    @Test
    public void insertReviewTest(){
        int countReviewsFirst = reviewService.getAllReviews().size();
        reviewService.insert(review);
        int countReviewsLast = reviewService.getAllReviews().size();
        assertEquals(1, countReviewsLast - countReviewsFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertNullReviewTest(){
        review.setDate(null);
        reviewService.insert(review);
    }

    @Test
    public void deleteReviewTest(){
        int countReviewsFirst = reviewService.getAllReviews().size();
        reviewService.delete(1);
        int countReviewsLast = reviewService.getAllReviews().size();
        assertEquals(1, countReviewsFirst - countReviewsLast);
    }

    @Test
    public void getReviewByIdTestTrue() {
        Review review = reviewService.getReviewById(2);
        assertEquals("Integer ac neque. Duis bibendum.", review.getText());
    }

    @Test
    public void getReviewByIdTestFalse() {
        Review review = reviewService.getReviewById(1001);
        assertEquals(null, review);
    }

    @Test
    public void getAllReviewsTestTrue() {
        List<Review> reviewList = reviewService.getAllReviews();
        assertEquals(1000, reviewList.size());
    }

    @Test
    public void getReviewsByUserIdTest() {
        List<Review> reviews = reviewService.getReviewsByUserId(4);
        assertEquals("Phasellus id sapien in sapien iaculis congue.", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTestTrue() {
        List<Review> reviews = reviewService.getReviewsByTourId(81);
        assertEquals("Fusce consequat. Nulla nisl.", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTestFasle() {
        List<Review> reviews = reviewService.getReviewsByTourId(86);
        assertTrue(reviews.isEmpty());
    }
}
