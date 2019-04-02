import java.math.BigDecimal
import java.time.LocalDate

import by.rekuts.travelagency.domain.{Tour, TourType}
import by.rekuts.travelagency.repository._
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import org.junit.{Assert, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class TourRepositoryImplTest {
  @Autowired
  private var tourRepository: TourRepository = _
  @Autowired
  private var hotelRepository: HotelRepository = _
  @Autowired
  private var countryRepository: CountryRepository = _

  @Test
  def insertTest(): Unit = {
    val tour = new Tour
    tour.photo_$eq("photo.jpg")
    tour.date_$eq(LocalDate.now)
    tour.duration_$eq(14)
    tour.description_$eq("Interesting tour")
    tour.cost_$eq(BigDecimal.valueOf(900))
    tour.tourType_$eq(TourType.cultural)
    tour.hotel_$eq(hotelRepository.getList(new HotelSpecification(85)).get(0))
    val specification = new CountrySpecification(67)
    tour.country_$eq(countryRepository.getList(specification).get(0))
    val countFirst = tourRepository.getList(new TourSpecification).size
    tourRepository.insert(tour)
    val countLast = tourRepository.getList(new TourSpecification).size
    Assert.assertEquals(1, countLast - countFirst)
  }

  @Test
  def deleteTest(): Unit = {
    val countFirst = tourRepository.getList(new TourSpecification).size
    tourRepository.delete(1)
    val countLast = tourRepository.getList(new TourSpecification).size
    Assert.assertEquals(1, countFirst - countLast)
  }

  @Test
  def getTourByIdTest(): Unit = {
    val tour = tourRepository.getList(new TourSpecification(1)).get(0)
    val description = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl."
    Assert.assertEquals(description, tour.description)
  }

  @Test
  def getAllToursTest(): Unit = {
    val tours = tourRepository.getList(new TourSpecification)
    Assert.assertEquals(1002, tours.size)
  }

  @Test
  def getToursByCriteriaTest(): Unit = {
    val specification = new TourSpecification
    specification.countryId_$eq(1)
    specification.minCost_$eq(scala.math.BigDecimal.valueOf(100))
    specification.maxCost_$eq(scala.math.BigDecimal.valueOf(800))
    specification.tourType_$eq(TourType.adventure)
    val tours = tourRepository.getList(specification)
    Assert.assertEquals(2, tours.size)
  }

  @Test
  def getToursByCriteriaTest2(): Unit = {
    val specification = new TourSpecification
    specification.duration_$eq(10)
    specification.minCost_$eq(scala.math.BigDecimal.valueOf(100))
    specification.stars_$eq(4)
    val tours = tourRepository.getList(specification)
    Assert.assertEquals(13, tours.size)
  }

  @Test
  def getToursByUserId(): Unit = {
    val specification = new TourSpecification
    specification.userId_$eq(101)
    val tours = tourRepository.getList(specification)
    Assert.assertEquals(2, tours.size)
  }
}
