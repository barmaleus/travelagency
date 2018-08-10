package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.repository.ReviewSpecification;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.repository.UserSpecification;
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
        review.setUser(userService.getList(new UserSpecification(5)).get(0));
        review.setTour(tourService.getList(new TourSpecification(47)).get(0));
    }

    @Test
    public void insertReviewTest(){
        int countReviewsFirst = reviewService.getList(new ReviewSpecification()).size();
        reviewService.insert(review);
        int countReviewsLast = reviewService.getList(new ReviewSpecification()).size();
        assertEquals(1, countReviewsLast - countReviewsFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertNullReviewTest(){
        review.setDate(null);
        reviewService.insert(review);
    }

    @Test
    public void deleteReviewTest(){
        int countReviewsFirst = reviewService.getList(new ReviewSpecification()).size();
        reviewService.delete(1);
        int countReviewsLast = reviewService.getList(new ReviewSpecification()).size();
        assertEquals(1, countReviewsFirst - countReviewsLast);
    }

    @Test
    public void getReviewByIdTestTrue() {
        Review review = reviewService.getList(new ReviewSpecification(2)).get(0);
        assertEquals("Integer ac neque. Duis bibendum.", review.getText());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getReviewByIdTestFalse1() {
        reviewService.getList(new ReviewSpecification(1001)).get(0);
    }

    @Test
    public void getReviewByIdTestFalse2() {
        List<Review> reviews = reviewService.getList(new ReviewSpecification(1001));
        assertTrue(reviews.isEmpty());
    }

    @Test
    public void getAllReviewsTestTrue() {
        List<Review> reviewList = reviewService.getList(new ReviewSpecification());
        assertEquals(1000, reviewList.size());
    }

    @Test
    public void getReviewsByUserIdTest() {
        ReviewSpecification specification = new ReviewSpecification();
        specification.setUserId(4);
        List<Review> reviews = reviewService.getList(specification);
        assertEquals("Phasellus id sapien in sapien iaculis congue.", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTestTrue() {
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(81);
        List<Review> reviews = reviewService.getList(specification);
        assertEquals("Fusce consequat. Nulla nisl.", reviews.get(0).getText());
    }

    @Test
    public void getReviewsByTourIdTestFasle() {
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(85);
        List<Review> reviews = reviewService.getList(specification);
        assertTrue(reviews.isEmpty());
    }
}
