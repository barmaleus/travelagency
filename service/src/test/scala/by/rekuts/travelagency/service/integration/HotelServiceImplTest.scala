package by.rekuts.travelagency.service.integration

import java.math.BigDecimal

import by.rekuts.travelagency.domain.Hotel
import by.rekuts.travelagency.repository.HotelSpecification
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.service.HotelService
import javax.persistence.PersistenceException
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.runner.RunWith
import org.junit.{Assert, Before, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class HotelServiceImplTest {
  @Autowired
  var hotelService: HotelService = _

  private val hotel = new Hotel

  @Before def initializeHotel(): Unit = {
    hotel.name_$eq("Tourist")
    hotel.stars_$eq(3)
    hotel.website_$eq("tourist.by")
    hotel.latitude_$eq(BigDecimal.valueOf(85.15465212))
    hotel.longitude_$eq(BigDecimal.valueOf(-48.85465516))
    hotel.features_$eq(List[String]())
  }

  @Test
  def insertHotelTest(): Unit = {
    val countHotelsFirst = hotelService.getList(new HotelSpecification).size
    hotelService.insert(hotel)
    val countHotelsLast = hotelService.getList(new HotelSpecification).size
    assertEquals(1, countHotelsLast - countHotelsFirst)
  }

  @Test(expected = classOf[PersistenceException])
  def insertNullHotelTest(): Unit = {
    hotel.name_$eq(null)
    hotelService.insert(hotel)
  }

  @Test
  def deleteHotelTestTrue(): Unit = {
    val countHotelsFirst = hotelService.getList(new HotelSpecification).size
    hotelService.delete(101)
    val countHotelsLast = hotelService.getList(new HotelSpecification).size
    assertEquals(1, countHotelsFirst - countHotelsLast)
  }

  @Test
  def deleteHotelTestFalseForeignKeys(): Unit = {
    val countHotelsFirst = hotelService.getList(new HotelSpecification).size
    hotelService.delete(1)
    val countHotelsLast = hotelService.getList(new HotelSpecification).size
    assertEquals(0, countHotelsFirst - countHotelsLast)
  }

  @Test
  def obtainHotelByIdTrue(): Unit = {
    val gotHotel = hotelService.getList(new HotelSpecification(101)).get(0)
    assertEquals("Tourist", gotHotel.name)
  }

  @Test(expected = classOf[IndexOutOfBoundsException])
  def obtainHotelByIdFalse1(): Unit = {
    hotelService.getList(new HotelSpecification(102)).get(0)
  }

  @Test
  def obtainHotelByIdFalse2(): Unit = {
    val hotels = hotelService.getList(new HotelSpecification(102))
    assertTrue(hotels.isEmpty)
  }

  @Test
  def obtainAllHotelsTestTrue(): Unit = {
    val hotels = hotelService.getList(new HotelSpecification)
    Assert.assertEquals(101, hotels.size)
  }

}
