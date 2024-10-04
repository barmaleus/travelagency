package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.HotelRepository;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.HotelService;
import by.rekuts.travelagency.service.exception.exception.DeleteException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final TourRepository tourRepository;

    @Transactional
    @Override
    public void insert(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Transactional
    @Override
    public void update(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Transactional
    @Override
    public void delete(int id) {
        List<Tour> tours = tourRepository.findAll(new TourSpecification());
        for (Tour tour : tours) {
            if (tour.getHotel().getId() == id) {
                throw new DeleteException(format(
                        "Hotel %s with id %s cannot be deleted because of tour with id %s referenced on it",
                        tour.getHotel().getName(), id, tour.getId())
                );
            }
        }
        hotelRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Hotel> getList(Specification<Hotel> specification) {
        return hotelRepository.findAll(specification);
    }
}
