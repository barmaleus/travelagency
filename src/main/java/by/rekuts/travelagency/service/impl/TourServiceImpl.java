package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.subjects.Tour;
import by.rekuts.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
