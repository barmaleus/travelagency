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

import java.util.List;

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
        Country country = new Country();
        country.setName("Užupis");
        CountrySpecification specification = new CountrySpecification();
        int countFirst = countryRepository.getList(specification).size();
        countryRepository.insert(country);
        int countLast = countryRepository.getList(specification).size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteCountryTest() {
        CountrySpecification specification = new CountrySpecification();
        specification.setId(230);
        int countFirst = countryRepository.getList(specification).size();
        countryRepository.delete(230);
        int countLast = countryRepository.getList(specification).size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getCountryByIdTrue() {
        Assert.assertEquals("Albania", (countryRepository.getList(new CountrySpecification(2)).get(0)).getName().trim());
    }

    @Test
    public void getAllCountriesTest() {
        CountrySpecification specification = new CountrySpecification();
        List<Country> countries = countryRepository.getList(specification);
        Assert.assertEquals(199, countries.size());
    }
}
