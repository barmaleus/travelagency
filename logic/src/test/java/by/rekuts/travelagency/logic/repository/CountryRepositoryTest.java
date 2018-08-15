//package by.rekuts.travelagency.logic.repository;
//
//import by.rekuts.travelagency.logic.config.TestRepositoryConfig;
//import by.rekuts.travelagency.logic.domain.Country;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.List;
//
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class CountryRepositoryTest {
//
//    @Autowired
//    CountryRepository countryRepository;
//
//    @Test
//    public void insertCountryTestTrue() {
//        Country country = new Country();
//        country.setName("Užupis");
//        CountrySpecification specification = new CountrySpecification();
//        int countFirst = countryRepository.getList(specification).size();
//        countryRepository.insert(country);
//        int countLast = countryRepository.getList(specification).size();
//        Assert.assertEquals(1, countLast - countFirst);
//    }
//
//    @Test
//    public void deleteCountryTest() {
//        CountrySpecification specification = new CountrySpecification();
//        specification.setId(230);
//        int countFirst = countryRepository.getList(specification).size();
//        countryRepository.delete(230);
//        int countLast = countryRepository.getList(specification).size();
//        Assert.assertEquals(1, countFirst - countLast);
//    }
//
//    @Test
//    public void getCountryByIdTrue() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
//        EntityManager em = emf.createEntityManager();
//        Country  country = em.find(Country.class, 2);
//        log.info("Found country: " + country.getName());
//        Assert.assertEquals(country.getName(), (countryRepository.getList(new CountrySpecification(2)).get(0)).getName());
//    }
//
//    @Test
//    public void getAllCountriesTest() {
//        CountrySpecification specification = new CountrySpecification();
//        List<Country> countries = countryRepository.getList(specification);
//        Assert.assertEquals(199, countries.size());
//    }
//}
