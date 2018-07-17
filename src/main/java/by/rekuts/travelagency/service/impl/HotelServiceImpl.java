package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.dao.subjects.Hotel;
import by.rekuts.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelDao hotelDao;

    @Override
    public void insert(Hotel hotel) {
        hotelDao.insert(hotel);
    }

    @Override
    public void delete(int id) {
        hotelDao.delete(id);
    }

    @Override
    public Hotel getHotelById(int id) {
        return hotelDao.getHotelById(id);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDao.getAllHotels();
    }
}
