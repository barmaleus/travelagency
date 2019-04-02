import java.math.BigDecimal

import by.rekuts.travelagency.domain.{Features, Hotel}
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.repository.{HotelRepository, HotelSpecification}
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
class HotelRepositoryTest {
  @Autowired
  private var hotelRepository: HotelRepository = _

  @Test
  def insertTest(): Unit = {
    val hotel = new Hotel
    hotel.name_$eq("Tourist")
    hotel.website_$eq("tourist.by")
    hotel.stars_$eq(4)
    hotel.latitude_$eq(BigDecimal.valueOf(44.5468))
    hotel.longitude_$eq(BigDecimal.valueOf(-78.64))
    hotel.features_$eq(List(Features.A.toString, Features.C.toString))
    val countFirst = hotelRepository.getList(new HotelSpecification).size
    hotelRepository.insert(hotel)
    val countLast = hotelRepository.getList(new HotelSpecification).size
    Assert.assertEquals(1, countLast - countFirst)
  }

  @Test
  def deleteHotelWithRefferenceTestFalse(): Unit = {
    val countFirst = hotelRepository.getList(new HotelSpecification).size
    hotelRepository.delete(100)
    val countLast = hotelRepository.getList(new HotelSpecification).size
    Assert.assertEquals(0, countFirst - countLast)
  }

  @Test
  def deleteHotelTestTrue(): Unit = {
    val countFirst = hotelRepository.getList(new HotelSpecification).size
    hotelRepository.delete(101)
    val countLast = hotelRepository.getList(new HotelSpecification).size
    Assert.assertEquals(1, countFirst - countLast)
  }

  @Test
  def getHotelByIdTestTrue(): Unit = {
    val hotel = hotelRepository.getList(new HotelSpecification(1)).get(0)
    Assert.assertEquals("Etiam Imperdiet Corporation", hotel.name)
  }

  @Test
  def getAllHotelsTestTrue(): Unit = {
    val hotels = hotelRepository.getList(new HotelSpecification)
    Assert.assertEquals(101, hotels.size)
  }
}
