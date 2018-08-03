package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.service.CountryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class CountryServiceImplTest {
    @Autowired
    CountryService countryService;

    @Test
    public void insertCountryTest(){
        Country country = new Country();
        country.setName("Any Country");
        int countCountriesFirst = countryService.getAllCountries().size();
        countryService.insert(country);
        int countCountriesLast = countryService.getAllCountries().size();
        assertEquals(1, countCountriesLast - countCountriesFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertNullCountryTest(){
        Country country = new Country();
        countryService.insert(country);
    }

    @Test
    public void deleteTest() {
        int countCountriesFirst = countryService.getAllCountries().size();
        countryService.delete(1);
        int countCountriesLast = countryService.getAllCountries().size();
        assertEquals(1, countCountriesFirst - countCountriesLast);
    }

    @Test
    public void getCountryByIdTestTrue(){
        Country country = countryService.getCountryById(2);
        assertEquals("Albania", country.getName().trim());
    }

    @Test
    public void getCountryByIdTestFalse(){
        Country country = countryService.getCountryById(2);
        assertNotEquals("Albania ", country.getName());
    }

    @Test
    public void getAllCountriesTestTrue(){
        List<Country> countryList = countryService.getAllCountries();
        assertEquals(199, countryList.size());
    }

    @Test
    public void getAllCountriesTestFalse() {
        List<Country> countryList = countryService.getAllCountries();
        assertNotEquals(0, countryList.size());
    }
}
