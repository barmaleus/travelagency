package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
@Transactional
public class CountryDaoImplTest {

    @Autowired
    CountryDao countryDao;

    @Test
    public void insertCountryTestTrue() {
        Country country = new Country();
        country.setName("Užupis");
        int countFirst = countryDao.getAllCountries().size();
        countryDao.insert(country);
        int countLast = countryDao.getAllCountries().size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteCountryTest() {
        int countFirst = countryDao.getAllCountries().size();
        countryDao.delete(230);
        int countLast = countryDao.getAllCountries().size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getCountryByIdTrue() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        Country  country = em.find(Country.class, 2);
        log.info("Found country: " + country.getName());
        Assert.assertEquals(country.getName(), countryDao.getCountryById(2).getName());
    }

    @Test
    public void getAllCountriesTest() {
        List<Country> countries = countryDao.getAllCountries();
        Assert.assertEquals(199, countries.size());
    }
}
