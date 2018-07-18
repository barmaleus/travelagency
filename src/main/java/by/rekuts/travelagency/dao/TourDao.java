package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Tour;

public interface TourDao {
	void insert(Tour tour);
	void delete(int id);
	Tour getTourById(int id);
	List<Tour> getAllTours();
}
