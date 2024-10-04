package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.repository.impl.CSVTourLoaderImpl;
import by.rekuts.travelagency.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final CSVTourLoaderImpl csvTourLoaderImpl;

    @Transactional
    @Override
    public void insert(Tour tour) {
        tourRepository.save(tour);
    }

    @Transactional
    @Override
    public void update(Tour tour) {
        tourRepository.save(tour);
    }

    @Transactional
    @Override
    public void delete(int id) {
        tourRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Tour> getList(Specification<Tour> specification) {
        return tourRepository.findAll(specification);
    }

    @Transactional
    @Override
    public void importCsvTours(String csvFileContent) {
        csvTourLoaderImpl.importCsvTours(csvFileContent);
    }
}
