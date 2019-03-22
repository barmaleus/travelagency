package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void insertCountryTestTrue() {
        var country = new Country();
        country.setName("Užupis");
        var specification = new CountrySpecification();
        var countFirst = countryRepository.getList(specification).size();
        countryRepository.insert(country);
        var countLast = countryRepository.getList(specification).size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteCountryTest() {
        var specification = new CountrySpecification();
        specification.setId(230);
        var countFirst = countryRepository.getList(specification).size();
        countryRepository.delete(230);
        var countLast = countryRepository.getList(specification).size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getCountryByIdTrue() {
        Assert.assertEquals("Albania", (countryRepository.getList(new CountrySpecification(2)).get(0)).getName().trim());
    }

    @Test
    public void getAllCountriesTest() {
        var specification = new CountrySpecification();
        var countries = countryRepository.getList(specification);
        Assert.assertEquals(199, countries.size());
    }
}
