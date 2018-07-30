package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.dao.subjects.Country;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
public class CountryDaoImplTest {

//    @Rule
//    public PreparedDbRule pg = EmbeddedPostgresRules.preparedDatabase(
//            FlywayPreparer.forClasspathLocation("db"));

    @Autowired
    CountryDao countryDao;

    @Test
    @Commit
//    @Ignore
    public void insertCountryTestTrue() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        Country country = new Country();
        country.setName("Sealand");
        em.getTransaction().begin();    //todo это работает, а declarative не работает
        em.persist(country);
        em.getTransaction().commit();
        //todo выполняется, но я не вижу изменений в базе rollbackается
    }

    @Test
    @Ignore
    public void deleteCountryTest() {
        int countFirst = countryDao.getAllCountries().size();
        countryDao.delete(199);
        int countLast = countryDao.getAllCountries().size();
        Assert.assertEquals(1, countFirst - countLast);
    }   //todo check

    //OK
    @Test
    public void getCountryByIdTrue() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        Country  country = em.find(Country.class, 2);
        log.info("Found country: " + country.getName());
        Assert.assertEquals(country.getName(), countryDao.getCountryById(2).getName());
    }

    //OK
    @Test
    public void getAllCountriesNamedQueryTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Country> query =
                em.createNamedQuery("Country.findAll", Country.class);
        List<Country> results = query.getResultList();
        List<Country> countries = countryDao.getAllCountries();
        if (results.size() == countries.size()) {
            for (int i = 0; i < countries.size(); i++) {
                if(!(countries.get(i).getId() == (results.get(i).getId())) ||
                        !countries.get(i).getName().equals(results.get(i).getName())) {
                    Assert.fail("Objects are not equals, line: " + i);
                }
            }
        } else {
            Assert.fail("Sizes are not the same");
        }
    }

    //OK
    @Test
    public void getAllCountriesCriteriaQueryTest() {
        List<Country> countries = countryDao.getAllCountries();
        Assert.assertEquals(198, countries.size());
    }
}
