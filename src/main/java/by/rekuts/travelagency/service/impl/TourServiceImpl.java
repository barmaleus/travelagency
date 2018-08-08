package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    TourRepository tourRepository;

    @Override
    public void insert(Tour tour) {
        tourRepository.insert(tour);
    }

    @Override
    public void delete(int id) {
        tourRepository.delete(id);
    }

    @Override
    public List<Tour> getList(Specification specification) {
        return tourRepository.getList(specification);
    }
}
