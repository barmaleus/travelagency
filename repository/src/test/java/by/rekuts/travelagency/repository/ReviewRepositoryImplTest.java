package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class ReviewRepositoryImplTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    TourRepository tourRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void insertReviewTestTrue() {
        var review = new Review();
        review.setText("Review text");
        review.setDate(LocalDateTime.now());
        review.setTour(tourRepository.getList(new TourSpecification(1)).get(0));
        review.setUser(userRepository.getList(new UserSpecification(1)).get(0));
        var countFirst = reviewRepository.getList(new ReviewSpecification()).size();
        reviewRepository.insert(review);
        var countLast = reviewRepository.getList(new ReviewSpecification()).size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteReviewTestTrue() {
        var countFirst = reviewRepository.getList(new ReviewSpecification()).size();
        reviewRepository.delete(1);
        var countLast = reviewRepository.getList(new ReviewSpecification()).size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getReviewByIdTest() {
        Assert.assertEquals("Integer ac neque. Duis bibendum.", reviewRepository.getList(new ReviewSpecification(2)).get(0).getText().trim());
    }

    @Test
    public void getAllReviewTest() {
        var reviews = reviewRepository.getList(new ReviewSpecification());
        Assert.assertEquals(1001, reviews.size());
    }

    @Test
    public void getReviewsForSpecificUser() {
        var userId = 1;
        var specification = new ReviewSpecification();
        specification.setUserId(userId);
        var reviews = reviewRepository.getList(specification);
        Assert.assertEquals(9, reviews.size());
    }

    @Test
    public void getReviewsForSpecificTour() {
        var tourId = 1;
        var specification = new ReviewSpecification();
        specification.setTourId(tourId);
        var reviews = reviewRepository.getList(specification);
        Assert.assertEquals(2, reviews.size());
    }
}
