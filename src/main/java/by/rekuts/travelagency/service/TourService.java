package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Tour;

import java.util.List;

public interface TourService {
    void insert(Tour tour);
    void delete(int id);
    Tour getTourById(int id);
    List<Tour> getAllTours();
}
