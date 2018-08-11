package by.rekuts.travelagency.logic.service.impl;

import by.rekuts.travelagency.logic.repository.Specification;
import by.rekuts.travelagency.logic.repository.TourRepository;
import by.rekuts.travelagency.logic.domain.Tour;
import by.rekuts.travelagency.logic.service.TourService;
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
