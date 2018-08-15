package by.rekuts.travelagency.logic.service.impl;

import by.rekuts.travelagency.logic.repository.HotelRepository;
import by.rekuts.travelagency.logic.repository.Specification;
import by.rekuts.travelagency.logic.domain.Hotel;
import by.rekuts.travelagency.logic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Transactional
    @Override
    public void insert(Hotel hotel) {
        hotelRepository.insert(hotel);
    }

    @Transactional
    @Override
    public void delete(int id) {
        hotelRepository.delete(id);
    }

    @Transactional
    @Override
    public List<Hotel> getList(Specification specification) {
        return hotelRepository.getList(specification);
    }
}
