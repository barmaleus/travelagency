//package by.rekuts.travelagency.logic.repository;
//
//import by.rekuts.travelagency.domain.config.TestRepositoryConfig;
//import by.rekuts.travelagency.domain.Review;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class ReviewRepositoryImplTest {
//
//    @Autowired
//    ReviewRepository reviewRepository;
//    @Autowired
//    TourRepository tourRepository;
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    public void insertReviewTestTrue() {
//        Review review = new Review();
//        review.setText("Review text");
//        review.setDate(LocalDateTime.now());
//        review.setTour(tourRepository.getList(new TourSpecification(1)).get(0));
//        review.setUser(userRepository.getList(new UserSpecification(1)).get(0));
//        int countFirst = reviewRepository.getList(new ReviewSpecification()).size();
//        reviewRepository.insert(review);
//        int countLast = reviewRepository.getList(new ReviewSpecification()).size();
//        Assert.assertEquals(1, countLast - countFirst);
//    }
//
//    @Test
//    public void deleteReviewTestTrue() {
//        int countFirst = reviewRepository.getList(new ReviewSpecification()).size();
//        reviewRepository.delete(1);
//        int countLast = reviewRepository.getList(new ReviewSpecification()).size();
//        Assert.assertEquals(1, countFirst - countLast);
//    }
//
//    @Test
//    public void getReviewByIdTest() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
//        EntityManager em = emf.createEntityManager();
//        Review  review = em.find(Review.class, 2);
//        log.info("Found review: " + review.getText());
//        Assert.assertEquals(review.getText(), reviewRepository.getList(new ReviewSpecification(2)).get(0).getText());
//    }
//
//    @Test
//    public void getAllReviewTest() {
//        List<Review> reviews = reviewRepository.getList(new ReviewSpecification());
//        Assert.assertEquals(1000, reviews.size());
//    }
//
//    @Test
//    public void getReviewsForSpecificUser() {
//        int userId = 1;
//        ReviewSpecification specification = new ReviewSpecification();
//        specification.setUserId(userId);
//        List<Review> reviews = reviewRepository.getList(specification);
//        Assert.assertEquals(9, reviews.size());
//    }
//
//    @Test
//    public void getReviewsForSpecificTour() {
//        int tourId = 1;
//        ReviewSpecification specification = new ReviewSpecification();
//        specification.setTourId(tourId);
//        List<Review> reviews = reviewRepository.getList(specification);
//        Assert.assertEquals(2, reviews.size());
//    }
//}
