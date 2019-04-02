import java.time.LocalDateTime

import by.rekuts.travelagency.domain.Review
import by.rekuts.travelagency.repository._
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import org.junit.runner.RunWith
import org.junit.{Assert, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class ReviewRepositoryImplTest {
  @Autowired
  private var reviewRepository: ReviewRepository = _
  @Autowired
  private var tourRepository: TourRepository = _
  @Autowired
  private var userRepository: UserRepository = _

  @Test
  def insertReviewTestTrue(): Unit = {
    val review = new Review
    review.text_$eq("Review text")
    review.date_$eq(LocalDateTime.now)
    review.tour_$eq(tourRepository.getList(new TourSpecification(1)).get(0))
    review.user_$eq(userRepository.getList(new UserSpecification(1)).get(0))
    val countFirst = reviewRepository.getList(new ReviewSpecification).size
    reviewRepository.insert(review)
    val countLast = reviewRepository.getList(new ReviewSpecification).size
    Assert.assertEquals(1, countLast - countFirst)
  }

  @Test
  def deleteReviewTestTrue(): Unit = {
    val countFirst = reviewRepository.getList(new ReviewSpecification).size
    reviewRepository.delete(1)
    val countLast = reviewRepository.getList(new ReviewSpecification).size
    Assert.assertEquals(1, countFirst - countLast)
  }

  @Test
  def obtainReviewByIdTest(): Unit = {
    Assert.assertEquals("Integer ac neque. Duis bibendum.", reviewRepository.getList(new ReviewSpecification(2)).get(0).text.trim)
  }

  @Test
  def obtainAllReviewTest(): Unit = {
    val reviews = reviewRepository.getList(new ReviewSpecification)
    Assert.assertEquals(1001, reviews.size)
  }

  @Test
  def obtainReviewsForSpecificUser(): Unit = {
    val userId = 1
    val specification = new ReviewSpecification
    specification.userId_$eq(userId)
    val reviews = reviewRepository.getList(specification)
    Assert.assertEquals(9, reviews.size)
  }

  @Test def obtainReviewsForSpecificTour(): Unit = {
    val tourId = 1
    val specification = new ReviewSpecification
    specification.tourId_$eq(tourId)
    val reviews = reviewRepository.getList(specification)
    Assert.assertEquals(2, reviews.size)
  }
}
