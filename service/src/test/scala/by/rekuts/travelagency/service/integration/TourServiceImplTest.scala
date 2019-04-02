package by.rekuts.travelagency.service.integration

import java.math.BigDecimal
import java.time.LocalDate

import by.rekuts.travelagency.domain.{Tour, TourType}
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.repository.{CountrySpecification, HotelSpecification, TourSpecification}
import by.rekuts.travelagency.service.{CountryService, HotelService, TourService}
import javax.persistence.PersistenceException
import org.junit.Assert.{assertEquals, assertTrue}
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
class TourServiceImplTest {
  @Autowired
  private var tourService: TourService = _
  @Autowired
  private var hotelService: HotelService = _
  @Autowired
  private var countryService: CountryService = _

  private val tour = new Tour

  @Before def initializeToursList(): Unit = {
    tour.photo_$eq("photo.jpg")
    tour.date_$eq(LocalDate.now)
    tour.duration_$eq(14)
    tour.description_$eq("Interesting tour1")
    tour.cost_$eq(BigDecimal.valueOf(900))
    tour.tourType_$eq(TourType.cultural)
    tour.hotel_$eq(hotelService.getList(new HotelSpecification(68)).get(0))
    tour.country_$eq(countryService.getList(new CountrySpecification(91)).get(0))
  }

  @Test
  def insertTest(): Unit = {
    val countToursFirst = tourService.getList(new TourSpecification).size
    tourService.insert(tour)
    val countToursLast = tourService.getList(new TourSpecification).size
    assertEquals(1, countToursLast - countToursFirst)
  }

  @Test(expected = classOf[PersistenceException])
  def insertTestFalse(): Unit = tourService.insert(new Tour)

  @Test
  def deleteTest(): Unit = {
    val countToursFirst = tourService.getList(new TourSpecification).size
    tourService.delete(1)
    val countToursLast = tourService.getList(new TourSpecification).size
    assertEquals(1, countToursFirst - countToursLast)
  }

  @Test
  def obtainTourByIdTestTrue(): Unit = {
    val tour = tourService.getList(new TourSpecification(4)).get(0)
    assertEquals("Nam dui.", tour.description)
  }

  @Test(expected = classOf[IndexOutOfBoundsException])
  def obtainTourByIdTestFalse1(): Unit = {
    tourService.getList(new TourSpecification(1001)).get(0)
  }

  @Test
  def obtainTourByIdTestFalse2(): Unit = {
    val tours = tourService.getList(new TourSpecification(1001))
    assertTrue(tours.isEmpty)
  }

  @Test
  def obtainAllToursTest(): Unit = {
    val tourList = tourService.getList(new TourSpecification)
    assertEquals(1002, tourList.size)
  }

  @Test
  def obtainToursByCriteria(): Unit = {
    val specification = new TourSpecification
    specification.tourType_$eq(TourType.safari)
    val result = tourService.getList(specification)
    assertEquals(58, result.size)
  }

  @Test
  def obtainToursByUserId(): Unit = {
    val specification = new TourSpecification
    specification.userId_$eq(101)
    val result = tourService.getList(specification)
    assertEquals(2, result.size)
  }
}
