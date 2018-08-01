package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.Tour;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("testScope")
@Transactional
public class TourDaoImplTest {

    @Autowired
    TourDao tourDao;

    @Test
    public void insertTest() {
        Tour tour = new Tour();
        tour.setPhoto("photo.jpg");
        tour.setDate(LocalDate.now());
        tour.setDuration(14);
        tour.setDescription("Interesting tour");
        tour.setCost(BigDecimal.valueOf(900));
        tour.setTourType(Tour.TourType.cultural);
        tour.setHotelId(85);
        tour.setCountryId(67);
        int countFirst = tourDao.getAllTours().size();
        tourDao.insert(tour);
        int countLast = tourDao.getAllTours().size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteTest() {
        int countFirst = tourDao.getAllTours().size();
        tourDao.delete(1);
        int countLast = tourDao.getAllTours().size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getTourByIdTest() {
        Tour tour = tourDao.getTourById(1);
        log.info("Found country: " + tour.getDescription());
        String description = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.";
        Assert.assertEquals(description, tour.getDescription());
    }

    @Test
    public void getAllToursTest() {
        List<Tour> tours = tourDao.getAllTours();
        Assert.assertEquals(1000, tours.size());
    }

    @Test   //todo ignore
    public void getToursByCriteriaTest() {
        List<Tour> tours = tourDao.getToursByCriteria(null, null, null, Tour.TourType.health, BigDecimal.valueOf(500.00), BigDecimal.valueOf(700.00), null);
        Assert.assertEquals(1000, tours.size());
    }
}
