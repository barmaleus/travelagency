package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class TourRepositoryImplTest {

    @Autowired
    TourRepository tourRepository;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    CountryRepository countryRepository;

    @Test
    public void insertTest() {
        var tour = new Tour();
        tour.setPhoto("photo.jpg");
        tour.setDate(LocalDate.now());
        tour.setDuration(14);
        tour.setDescription("Interesting tour");
        tour.setCost(BigDecimal.valueOf(900));
        tour.setTourType(Tour.TourType.cultural);
        tour.setHotel(hotelRepository.getList(new HotelSpecification(85)).get(0));
        var specification = new CountrySpecification(67);
        tour.setCountry(countryRepository.getList(specification).get(0));
        var countFirst = tourRepository.getList(new TourSpecification()).size();
        tourRepository.insert(tour);
        var countLast = tourRepository.getList(new TourSpecification()).size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteTest() {
        var countFirst = tourRepository.getList(new TourSpecification()).size();
        tourRepository.delete(1);
        var countLast = tourRepository.getList(new TourSpecification()).size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getTourByIdTest() {
        var tour = tourRepository.getList(new TourSpecification(1)).get(0);
        var description = "Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.";
        Assert.assertEquals(description, tour.getDescription());
    }

    @Test
    public void getAllToursTest() {
        var tours = tourRepository.getList(new TourSpecification());
        Assert.assertEquals(1002, tours.size());
    }

    @Test
    public void getToursByCriteriaTest() {
        var specification = new TourSpecification();
        specification.setCountryId(1);
        specification.setMinCost(BigDecimal.valueOf(100));
        specification.setMinCost(BigDecimal.valueOf(800));
        specification.setTourType(Tour.TourType.adventure);
        var tours = tourRepository.getList(specification);
        Assert.assertEquals(2, tours.size());
    }

    @Test
    public void getToursByCriteriaTest2() {
        var specification = new TourSpecification();
        specification.setDuration(10);
        specification.setMinCost(BigDecimal.valueOf(100));
        specification.setStars(4);
        var tours = tourRepository.getList(specification);
        Assert.assertEquals(13, tours.size());
    }

    @Test
    public void getToursByUserId() {
        var specification = new TourSpecification();
        specification.setUserId(101);
        var tours = tourRepository.getList(specification);
        Assert.assertEquals(2, tours.size());
    }
}
