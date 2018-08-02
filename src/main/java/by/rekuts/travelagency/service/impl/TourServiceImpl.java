package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    TourDao tourDao;

    @Override
    public void insert(Tour tour) {
        tourDao.insert(tour);
    }

    @Override
    public void delete(int id) {
        tourDao.delete(id);
    }

    @Override
    public Tour getTourById(int id) {
        return tourDao.getTourById(id);
    }

    @Override
    public List<Tour> getAllTours() {
        return tourDao.getAllTours();
    }

    @Override
    public List<Tour> getToursByCriteria(Country country, LocalDate date, Integer duration, Tour.TourType tourType, BigDecimal minCost, BigDecimal maxCost, Integer stars) {
        return tourDao.getToursByCriteria(country, date, duration, tourType, minCost, maxCost, stars);
    }

    @Override
    public List<Tour> getToursByUserId(int userId) {
        return tourDao.getToursByUserId(userId);
    }
}
