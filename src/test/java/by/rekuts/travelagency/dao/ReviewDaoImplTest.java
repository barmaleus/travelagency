package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("testScope")
@Transactional
public class ReviewDaoImplTest {

    @Autowired
    ReviewDao reviewDao;
    @Autowired
    TourDao tourDao;
    @Autowired
    UserDao userDao;

    @Test
    public void insertReviewTestTrue() {
        Review review = new Review();
        review.setText("Review text");
        review.setTour(tourDao.getTourById(1));
        review.setUser(userDao.getUserById(1));
        int countFirst = reviewDao.getAllReviews().size();
        reviewDao.insert(review);
        int countLast = reviewDao.getAllReviews().size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteReviewTestTrue() {
        int countFirst = reviewDao.getAllReviews().size();
        reviewDao.delete(1);
        int countLast = reviewDao.getAllReviews().size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getReviewByIdTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        Review  review = em.find(Review.class, 2);
        log.info("Found review: " + review.getText());
        Assert.assertEquals(review.getText(), reviewDao.getReviewById(2).getText());
    }

    @Test
    public void getAllReviewTest() {
        List<Review> reviews = reviewDao.getAllReviews();
        Assert.assertEquals(1000, reviews.size());
    }

    @Test
    public void getReviewsForSpecificUser() {
        int userId = 1;
        List<Review> reviews = reviewDao.getReviewsByUserId(userId);
        Assert.assertEquals(9, reviews.size());
    }

    @Test
    public void getReviewsForSpecificTour() {
        int userId = 1;
        List<Review> reviews = reviewDao.getReviewsByTourId(userId);
        Assert.assertEquals(2, reviews.size());
    }
}
