package by.rekuts.travelagency.service.mocktests

import java.math.BigDecimal
import java.util

import by.rekuts.travelagency.domain.Hotel
import by.rekuts.travelagency.repository.HotelSpecification
import by.rekuts.travelagency.service.impl.HotelServiceImpl
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.junit.{Before, Test}
import org.mockito.Matchers.{any, anyInt}
import org.mockito.Mockito._

class HotelServiceImplTest {
  private var hotels: List[Hotel] = _

  @Before
  def initializeHotelsList(): Unit = {
    val hotel1 = new Hotel
    hotel1.id_$eq(1)
    hotel1.name_$eq("Tourist")
    hotel1.stars_$eq(3)
    hotel1.website_$eq("tourist.by")
    hotel1.latitude_$eq(BigDecimal.valueOf(85.15465212))
    hotel1.longitude_$eq(BigDecimal.valueOf(-48.85465516))
    hotel1.features_$eq(List[String]())
    val hotel2 = new Hotel
    hotel2.id_$eq(2)
    hotel2.name_$eq("Red Dragon")
    hotel2.stars_$eq(3)
    hotel2.website_$eq("redragon.cn")
    hotel2.latitude_$eq(BigDecimal.valueOf(85.15465212))
    hotel2.longitude_$eq(BigDecimal.valueOf(-48.85465516))
    hotel2.features_$eq(List[String]())
    val hotel3 = new Hotel
    hotel3.id_$eq(3)
    hotel3.name_$eq("Little Italia")
    hotel3.stars_$eq(3)
    hotel3.website_$eq("l-italia.by")
    hotel3.latitude_$eq(BigDecimal.valueOf(85.15465212))
    hotel3.longitude_$eq(BigDecimal.valueOf(-48.85465516))
    hotel3.features_$eq(List[String]())
    hotels = List[Hotel](hotel1, hotel2, hotel3)
  }

  @Test
  def insertTest(): Unit = {
    val hotelService = mock(classOf[HotelServiceImpl])
    doNothing.when(hotelService).insert(any(classOf[Hotel]))
    hotelService.insert(any(classOf[Hotel]))
    hotelService.insert(any(classOf[Hotel]))
    verify(hotelService, times(2)).insert(any(classOf[Hotel]))
  }

  @Test
  def deleteTest(): Unit = {
    val hotelService = mock(classOf[HotelServiceImpl])
    doNothing.when(hotelService).insert(any(classOf[Hotel]))
    hotelService.delete(6)
    hotelService.delete(8)
    verify(hotelService, times(2)).delete(anyInt)
  }

  @Test
  def obtainHotelByIdTestTrue(): Unit = {
    val hotelService = mock(classOf[HotelServiceImpl])
    val specification = new HotelSpecification(2)
    val singletonList = util.Arrays.asList(hotels(1))
    when(hotelService.getList(specification)).thenReturn(singletonList)
    val hotel = hotelService.getList(specification).get(0)
    assertEquals("Red Dragon", hotel.name)
  }

  @Test
  def obtainHotelByIdTestFalse(): Unit = {
    val hotelService = mock(classOf[HotelServiceImpl])
    val specification = new HotelSpecification(2)
    val singletonList = util.Arrays.asList(hotels(1))
    when(hotelService.getList(specification)).thenReturn(singletonList)
    val hotel = hotelService.getList(specification).get(0)
    assertNotEquals("Tourist", hotel.name)
  }

  @Test
  def obtainAllHotelsTestTrue(): Unit = {
    val hotelServiceMock = mock(classOf[HotelServiceImpl])
    val specification = new HotelSpecification
    val hotelsList = new util.ArrayList[Hotel]()
    hotels.foreach(hotel => hotelsList.add(hotel))
    when(hotelServiceMock.getList(specification)).thenReturn(hotelsList)
    val hotelList = hotelServiceMock.getList(specification)
    assertEquals(3, hotelList.size)
  }

  @Test
  def obtainAllHotelsTestFalse(): Unit = {
    val hotelServiceMock = mock(classOf[HotelServiceImpl])
    val specification = new HotelSpecification
    val hotelsList = new util.ArrayList[Hotel]()
    hotels.foreach(hotel => hotelsList.add(hotel))
    when(hotelServiceMock.getList(specification)).thenReturn(hotelsList)
    val hotelList = hotelServiceMock.getList(specification)
    assertNotEquals(0, hotelList.size)
  }
}
