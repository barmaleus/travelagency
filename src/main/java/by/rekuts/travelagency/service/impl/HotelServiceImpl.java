package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.repository.HotelRepository;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Override
    public void insert(Hotel hotel) {
        hotelRepository.insert(hotel);
    }

    @Override
    public void delete(int id) {
        hotelRepository.delete(id);
    }

    @Override
    public List<Hotel> getList(Specification specification) {
        return hotelRepository.getList(specification);
    }
}
