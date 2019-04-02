package by.rekuts.travelagency.service.integration

import java.time.LocalDateTime

import by.rekuts.travelagency.domain.Review
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.repository.{ReviewSpecification, TourSpecification, UserSpecification}
import by.rekuts.travelagency.service.{ReviewService, TourService, UserService}
import javax.persistence.PersistenceException
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class ReviewServiceImplTest {
  @Autowired
  private var reviewService: ReviewService = _
  @Autowired
  private var userService: UserService = _
  @Autowired
  private var tourService: TourService = _

  private val review = new Review

  @Before
  def initializeReview(): Unit = {
    review.date_$eq(LocalDateTime.now)
    review.text_$eq("Interesting tour")
    review.user_$eq(userService.getList(new UserSpecification(5)).get(0))
    review.tour_$eq(tourService.getList(new TourSpecification(47)).get(0))
  }

  @Test
  def insertReviewTest(): Unit = {
    val countReviewsFirst = reviewService.getList(new ReviewSpecification).size
    reviewService.insert(review)
    val countReviewsLast = reviewService.getList(new ReviewSpecification).size
    assertEquals(1, countReviewsLast - countReviewsFirst)
  }

  @Test(expected = classOf[PersistenceException])
  def insertNullReviewTest(): Unit = {
    review.date_$eq(null)
    reviewService.insert(review)
  }

  @Test
  def deleteReviewTest(): Unit = {
    val countReviewsFirst = reviewService.getList(new ReviewSpecification).size
    reviewService.delete(1)
    val countReviewsLast = reviewService.getList(new ReviewSpecification).size
    assertEquals(1, countReviewsFirst - countReviewsLast)
  }

  @Test
  def obtainReviewByIdTestTrue(): Unit = {
    val review = reviewService.getList(new ReviewSpecification(2)).get(0)
    assertEquals("Integer ac neque. Duis bibendum.", review.text)
  }

  @Test(expected = classOf[IndexOutOfBoundsException])
  def obtainReviewByIdTestFalse1(): Unit = {
    reviewService.getList(new ReviewSpecification(1001)).get(0)
  }

  @Test
  def obtainReviewByIdTestFalse2(): Unit = {
    val reviews = reviewService.getList(new ReviewSpecification(1001))
    assertTrue(reviews.isEmpty)
  }

  @Test
  def obtainAllReviewsTestTrue(): Unit = {
    val reviewList = reviewService.getList(new ReviewSpecification)
    assertEquals(1001, reviewList.size)
  }

  @Test
  def obtainReviewsByUserIdTest(): Unit = {
    val specification = new ReviewSpecification
    specification.userId_$eq(4)
    val reviews = reviewService.getList(specification)
    assertEquals("Phasellus id sapien in sapien iaculis congue.", reviews.get(0).text)
  }

  @Test
  def obtainReviewsByTourIdTestTrue(): Unit = {
    val specification = new ReviewSpecification
    specification.tourId_$eq(81)
    val reviews = reviewService.getList(specification)
    assertEquals("Fusce consequat. Nulla nisl.", reviews.get(0).text)
  }

  @Test
  def obtainReviewsByTourIdTestFasle(): Unit = {
    val specification = new ReviewSpecification
    specification.tourId_$eq(85)
    val reviews = reviewService.getList(specification)
    assertTrue(reviews.isEmpty)
  }
}
