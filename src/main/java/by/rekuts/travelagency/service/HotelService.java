package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Hotel;

import java.util.List;

public interface HotelService {
    void insert(Hotel hotel);
    void delete(int id);
    Hotel getHotelById(int id);
    List<Hotel> getAllHotels();
}
