package by.rekuts.travelagency.service.mocktests

import java.time.LocalDateTime
import java.util

import by.rekuts.travelagency.domain.{Review, Tour, User}
import by.rekuts.travelagency.repository.ReviewSpecification
import by.rekuts.travelagency.service.impl.ReviewServiceImpl
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.junit.{Before, Test}
import org.mockito.Matchers.{any, anyInt}
import org.mockito.Mockito._

class ReviewServiceImplTest {
  private var reviews: util.List[Review] = _

  @Before
  def initializeReviewsList(): Unit = {
    val user1 = new User
    user1.id_$eq(5)
    val tour1 = new Tour
    tour1.id_$eq(67)
    val review1 = new Review
    review1.id_$eq(1)
    review1.date_$eq(LocalDateTime.now)
    review1.text_$eq("review text1")
    review1.user_$eq(user1)
    review1.tour_$eq(tour1)
    val user2 = new User
    user2.id_$eq(4)
    val tour2 = new Tour
    tour2.id_$eq(86)
    val review2 = new Review
    review2.id_$eq(2)
    review2.date_$eq(LocalDateTime.now)
    review2.text_$eq("review text2")
    review2.user_$eq(user2)
    review2.tour_$eq(tour2)
    val user3 = new User
    user3.id_$eq(9)
    val tour3 = new Tour
    tour3.id_$eq(3)
    val review3 = new Review
    review3.id_$eq(3)
    review3.date_$eq(LocalDateTime.now)
    review3.text_$eq("review text3")
    review3.user_$eq(user3)
    review3.tour_$eq(tour3)
    reviews = util.List.of(review1, review2, review3)
  }

  @Test
  def insertTest(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    doNothing.when(reviewService).insert(any(classOf[Review]))
    reviewService.insert(new Review)
    reviewService.insert(new Review)
    verify(reviewService, times(2)).insert(any(classOf[Review]))
  }

  @Test
  def deleteTest(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    doNothing.when(reviewService).insert(any(classOf[Review]))
    reviewService.delete(6)
    reviewService.delete(8)
    verify(reviewService, times(2)).delete(anyInt)
  }

  @Test
  def obtainReviewByIdTestTrue(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    val specification = new ReviewSpecification(2)
    val singletonList = util.Arrays.asList(reviews.get(1))
    when(reviewService.getList(specification)).thenReturn(singletonList)
    val review = reviewService.getList(specification).get(0)
    assertEquals("review text2", review.text)
  }

  @Test
  def obtainReviewByIdTestFalse(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    val specification = new ReviewSpecification(2)
    val singletonList = util.Arrays.asList(reviews.get(1))
    when(reviewService.getList(specification)).thenReturn(singletonList)
    val review = reviewService.getList(specification).get(0)
    assertNotEquals("review text1", review.text)
  }

  @Test
  def obtainAllReviewsTestTrue(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    val specification = new ReviewSpecification
    when(reviewService.getList(specification)).thenReturn(reviews)
    val reviewList = reviewService.getList(specification)
    assertEquals(3, reviewList.size)
  }

  @Test
  def obtainAllReviewsTestFalse(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    val specification = new ReviewSpecification
    when(reviewService.getList(specification)).thenReturn(reviews)
    val reviewList = reviewService.getList(specification)
    assertNotEquals(0, reviewList.size)
  }

  @Test
  def obtainReviewsByUserIdTest(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    val specification = new ReviewSpecification
    specification.userId_$eq(4)
    val singletonList = util.Arrays.asList(reviews.get(1))
    when(reviewService.getList(specification)).thenReturn(singletonList)
    val resultReviews = reviewService.getList(specification)
    assertEquals("review text2", resultReviews.get(0).text)
  }

  @Test
  def obtainReviewsByTourIdTest(): Unit = {
    val reviewService = mock(classOf[ReviewServiceImpl])
    val specification = new ReviewSpecification
    specification.tourId_$eq(86)
    val singletonList = util.Arrays.asList(reviews.get(1))
    when(reviewService.getList(specification)).thenReturn(singletonList)
    val resultReviews = reviewService.getList(specification)
    assertEquals("review text2", resultReviews.get(0).text)
  }
}
