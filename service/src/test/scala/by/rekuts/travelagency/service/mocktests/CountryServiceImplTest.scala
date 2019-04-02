package by.rekuts.travelagency.service.mocktests

import java.util

import by.rekuts.travelagency.domain.Country
import by.rekuts.travelagency.repository.CountrySpecification
import by.rekuts.travelagency.service.impl.CountryServiceImpl
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.junit.{Before, Test}
import org.mockito.Matchers.{any, anyInt}
import org.mockito.Mockito._

class CountryServiceImplTest {
  private val country = new Country

  private var countries: util.List[Country] = _

  private val countryServiceMock = mock(classOf[CountryServiceImpl])

  @Before
  def initializeCountriesList(): Unit = {
    country.id_$eq(3525)
    country.name_$eq("Any Country")
    val country1 = new Country
    country1.id_$eq(1)
    country1.name_$eq("Russia")
    val country2 = new Country
    country2.id_$eq(2)
    country2.name_$eq("India")
    val country3 = new Country
    country3.id_$eq(3)
    country3.name_$eq("China")
    countries = util.List.of(country1, country2, country3)
  }

  @Test
  def insertCountryMockTest(): Unit = {
    doNothing.when(countryServiceMock).insert(new Country)
    countryServiceMock.insert(new Country)
    countryServiceMock.insert(country)
    verify(countryServiceMock, times(2)).insert(any(classOf[Country]))
  }

  @Test
  def deleteTest(): Unit = {
    doNothing.when(countryServiceMock).insert(new Country)
    countryServiceMock.delete(1)
    countryServiceMock.insert(country)
    verify(countryServiceMock, times(1)).delete(anyInt)
  }

  @Test
  def obtainCountryByIdTestTrue(): Unit = {
    val specification = new CountrySpecification(2)
    val singletonList = util.Arrays.asList(countries.get(1))
    when(countryServiceMock.getList(specification)).thenReturn(singletonList)
    val country = countryServiceMock.getList(specification).get(0)
    assertEquals("India", country.name)
  }

  @Test
  def obtainCountryByIdTestFalse(): Unit = {
    val specification = new CountrySpecification(2)
    val singletonList = util.Arrays.asList(countries.get(1))
    when(countryServiceMock.getList(specification)).thenReturn(singletonList)
    val country = countryServiceMock.getList(specification).get(0)
    assertNotEquals("Russia", country.name)
  }

  @Test
  def obtainAllCountriesTestTrue(): Unit = {
    val specification = new CountrySpecification
    when(countryServiceMock.getList(specification)).thenReturn(countries)
    val countryList = countryServiceMock.getList(specification)
    assertEquals(3, countryList.size)
  }

  @Test
  def obtainAllCountriesTestFalse(): Unit = {
    val specification = new CountrySpecification
    when(countryServiceMock.getList(specification)).thenReturn(countries)
    val countryList = countryServiceMock.getList(specification)
    assertNotEquals(0, countryList.size)
  }
}
