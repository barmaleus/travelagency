package by.rekuts.travelagency.service.mocktests

import java.math.BigDecimal
import java.time.LocalDate
import java.util

import by.rekuts.travelagency.domain.{Country, Hotel, Tour, TourType}
import by.rekuts.travelagency.repository.TourSpecification
import by.rekuts.travelagency.service.impl.TourServiceImpl
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.junit.{Before, Test}
import org.mockito.Matchers.{any, anyInt}
import org.mockito.Mockito._

class TourServiceImplTest {
  private var tours: util.List[Tour] = _

  @Before
  def initializeToursList(): Unit = {
    val hotel1 = new Hotel
    hotel1.id_$eq(68)
    val country1 = new Country
    country1.id_$eq(91)
    val tour1 = new Tour
    tour1.id_$eq(1)
    tour1.photo_$eq("photo.jpg")
    tour1.date_$eq(LocalDate.now)
    tour1.duration_$eq(14)
    tour1.description_$eq("Interesting tour1")
    tour1.cost_$eq(BigDecimal.valueOf(900))
    tour1.tourType_$eq(TourType.cultural)
    tour1.hotel_$eq(hotel1)
    tour1.country_$eq(country1)
    val hotel2 = new Hotel
    hotel2.id_$eq(99)
    val country2 = new Country
    country2.id_$eq(16)
    val tour2 = new Tour
    tour2.id_$eq(2)
    tour2.photo_$eq("photo.jpg")
    tour2.date_$eq(LocalDate.now)
    tour2.duration_$eq(14)
    tour2.description_$eq("Interesting tour2")
    tour2.cost_$eq(BigDecimal.valueOf(900))
    tour2.tourType_$eq(TourType.safari)
    tour2.hotel_$eq(hotel2)
    tour2.country_$eq(country2)
    val hotel3 = new Hotel
    hotel3.id_$eq(33)
    val country3 = new Country
    country3.id_$eq(14)
    val tour3 = new Tour
    tour3.id_$eq(3)
    tour3.photo_$eq("photo.jpg")
    tour3.date_$eq(LocalDate.now)
    tour3.duration_$eq(14)
    tour3.description_$eq("Interesting tour3")
    tour3.cost_$eq(BigDecimal.valueOf(900))
    tour3.tourType_$eq(TourType.safari)
    tour3.hotel_$eq(hotel3)
    tour1.country_$eq(country3)
    tours = util.List.of(tour1, tour2, tour3)
  }

  @Test
  def insertTest(): Unit = {
    val tourService = mock(classOf[TourServiceImpl])
    doNothing.when(tourService).insert(any(classOf[Tour]))
    tourService.insert(any(classOf[Tour]))
    tourService.insert(any(classOf[Tour]))
    verify(tourService, times(2)).insert(any(classOf[Tour]))
  }

  @Test
  def deleteTest(): Unit = {
    val tourService = mock(classOf[TourServiceImpl])
    doNothing.when(tourService).insert(any(classOf[Tour]))
    tourService.delete(6)
    tourService.delete(8)
    verify(tourService, times(2)).delete(anyInt)
  }

  @Test
  def obtainTourByIdTestTrue(): Unit = {
    val tourService = mock(classOf[TourServiceImpl])
    val specification = new TourSpecification(2)
    val singletonList = util.Arrays.asList(tours.get(1))
    when(tourService.getList(specification)).thenReturn(singletonList)
    val tour = tourService.getList(specification).get(0)
    assertEquals("Interesting tour2", tour.description)
  }

  @Test
  def obtainTourByIdTestFalse(): Unit = {
    val tourService = mock(classOf[TourServiceImpl])
    val specification = new TourSpecification(2)
    val singletonList = util.Arrays.asList(tours.get(1))
    when(tourService.getList(specification)).thenReturn(singletonList)
    val tour = tourService.getList(specification).get(0)
    assertNotEquals("Interesting tour1", tour.description)
  }

  @Test
  def obtainAllToursTestTrue(): Unit = {
    val tourService = mock(classOf[TourServiceImpl])
    val specification = new TourSpecification
    when(tourService.getList(specification)).thenReturn(tours)
    val tourList = tourService.getList(specification)
    assertEquals(3, tourList.size)
  }

  @Test
  def obtainAllToursTestFalse(): Unit = {
    val tourService = mock(classOf[TourServiceImpl])
    val specification = new TourSpecification
    when(tourService.getList(specification)).thenReturn(tours)
    val tourList = tourService.getList(specification)
    assertNotEquals(0, tourList.size)
  }
}
