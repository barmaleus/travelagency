package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.dao.subjects.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
public class ReviewDaoImplTest {

    @Autowired
    ReviewDao reviewDao;

    @Test
//    @Ignore
    public void insertReviewTestTrue() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        Review review = new Review();
        review.setText("Review text");
        em.persist(review);
        //todo выполняется, но я не вижу изменений в базе rollbackается
    }

    @Test
    @Ignore
    public void deleteReviewTestTrue() {
        int countFirst = reviewDao.getAllReviews().size();
        reviewDao.delete(1001);
        int countLast = reviewDao.getAllReviews().size();
        Assert.assertEquals(1, countFirst - countLast);
    }   //todo check

    //OK
    @Test
    public void getReviewByIdTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        Review  review = em.find(Review.class, 2);
        log.info("Found review: " + review.getText());
        Assert.assertEquals(review.getText(), reviewDao.getReviewById(2).getText());
    }

    //OK
    @Test
    public void getAllReviewTest() {
        List<Review> reviews = reviewDao.getAllReviews();
        Assert.assertEquals(1000, reviews.size());
    }
}
