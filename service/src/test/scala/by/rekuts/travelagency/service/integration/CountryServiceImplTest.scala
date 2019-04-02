package by.rekuts.travelagency.service.integration

import by.rekuts.travelagency.domain.Country
import by.rekuts.travelagency.repository.CountrySpecification
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.service.CountryService
import javax.persistence.PersistenceException
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class CountryServiceImplTest {
  @Autowired
  private var countryService: CountryService = _

  @Test
  def insertCountryTest(): Unit = {
    val country = new Country
    country.name_$eq("Any Country")
    val countCountriesFirst = countryService.getList(new CountrySpecification).size
    countryService.insert(country)
    val countCountriesLast = countryService.getList(new CountrySpecification).size
    assertEquals(1, countCountriesLast - countCountriesFirst)
  }

  @Test(expected = classOf[PersistenceException])
  def insertNullCountryTest(): Unit = countryService.insert(new Country)

  @Test
  def deleteTest(): Unit = {
    val countCountriesFirst = countryService.getList(new CountrySpecification).size
    countryService.delete(55)
    val countCountriesLast = countryService.getList(new CountrySpecification).size
    assertEquals(1, countCountriesFirst - countCountriesLast)
  }

  @Test(expected = classOf[PersistenceException])
  def deleteTestFalse(): Unit = {
    val countCountriesFirst = countryService.getList(new CountrySpecification).size
    countryService.delete(1)
    val countCountriesLast = countryService.getList(new CountrySpecification).size
    assertEquals(1, countCountriesFirst - countCountriesLast)
  }

  @Test
  def obtainCountryByIdTestTrue(): Unit = {
    val country = countryService.getList(new CountrySpecification(2)).get(0)
    assertEquals("Albania", country.name.trim)
  }

  @Test
  def obtainCountryByIdTestFalse(): Unit = {
    val country = countryService.getList(new CountrySpecification(2)).get(0)
    assertNotEquals("Albania ", country.name)
  }

  @Test
  def obtainAllCountriesTestTrue(): Unit = {
    val countryList = countryService.getList(new CountrySpecification)
    assertEquals(199, countryList.size)
  }

  @Test
  def obtainAllCountriesTestFalse(): Unit = {
    val countryList = countryService.getList(new CountrySpecification)
    assertNotEquals(0, countryList.size)
  }
}
