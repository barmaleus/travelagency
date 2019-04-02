import java.util

import by.rekuts.travelagency.domain.Country
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.repository.{CountryRepository, CountrySpecification}
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
class CountryRepositoryTest {
  @Autowired
  private var countryRepository: CountryRepository = _

  @Test
  def insertCountryTestTrue(): Unit = {
    val country: Country = new Country
    country.name_$eq("Užupis")
    val specification: CountrySpecification = new CountrySpecification
    val countFirst: Int = countryRepository.getList(specification).size
    countryRepository.insert(country)
    val countLast: Int = countryRepository.getList(specification).size
    Assert.assertEquals(1, countLast - countFirst)
  }

  @Test def deleteCountryTest(): Unit = {
    val specification: CountrySpecification = new CountrySpecification
    specification.id_$eq(230)
    val countFirst: Int = countryRepository.getList(specification).size
    countryRepository.delete(230)
    val countLast: Int = countryRepository.getList(specification).size
    Assert.assertEquals(1, countFirst - countLast)
  }

  @Test def receiveCountryByIdTrue(): Unit = {
    Assert.assertEquals("Albania", countryRepository.getList(new CountrySpecification(2)).get(0).name.trim)
  }

  @Test def receiveAllCountriesTest(): Unit = {
    val specification: CountrySpecification = new CountrySpecification
    val countries: util.List[Country] = countryRepository.getList(specification)
    Assert.assertEquals(199, countries.size)
  }
}
