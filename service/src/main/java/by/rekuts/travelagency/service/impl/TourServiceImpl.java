package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.repository.impl.CSVTourLoaderImpl;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    TourRepository tourRepository;
    @Autowired
    CSVTourLoaderImpl csvTourLoaderImpl;

    @Transactional
    @Override
    public void insert(Tour tour) {
        tourRepository.insert(tour);
    }

    @Transactional
    @Override
    public void update(Tour tour) {
        tourRepository.update(tour);
    }

    @Transactional
    @Override
    public void delete(int id) {
        tourRepository.delete(id);
    }

    @Transactional
    @Override
    public List<Tour> getList(Specification specification) {
        return tourRepository.getList(specification);
    }

    @Transactional
    @Override
    public void importCsvTours(String csvFileContent) {
        csvTourLoaderImpl.importCsvTours(csvFileContent);
    }
}
