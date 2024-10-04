package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.CountryRepository;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.CountryService;
import by.rekuts.travelagency.service.exception.exception.DeleteException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final TourRepository tourRepository;

    @Transactional
    @Override
    public void insert(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    @Override
    public void update(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    @Override
    public void delete(int id) {
        countryRepository.deleteById(id);

        List<Tour> tours = tourRepository.findAll(new TourSpecification());
        for (Tour tour : tours) {
            if (tour.getCountry().getId() == id) {
                throw new DeleteException(format(
                        "Country %s with id %s cannot be deleted because of tour with id %s referenced on it",
                        tour.getCountry().getName(), id, tour.getId()));
            }
        }
    }

    @Transactional
    @Override
    public List<Country> getList(Specification<Country> specification) {
        return countryRepository.findAll(specification);
    }
}
