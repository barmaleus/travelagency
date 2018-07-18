package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Hotel;

public interface HotelDao {
	void insert(Hotel hotel);
	void delete(int id);
	Hotel getHotelById(int id);
	List<Hotel> getAllHotels();
}
