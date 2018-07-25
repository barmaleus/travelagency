package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.dao.subjects.Tour;

import java.util.List;

/**
 * Interface TourService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of TourDao interface and could call its methods.
 */
public interface TourService {

    /**
     * This method calls {@link TourDao#insert(Tour)}
     * It inserts object of Tour class to database
     * @param tour - object of the tour
     * @see Tour
     */
    void insert(Tour tour);

    /**
     * This method calls {@link TourDao#delete(int)}
     * It deletes tour with id <b>id</b> from database
     * @param id - id of the tour
     * @see Tour#id
     */
    void delete(int id);

    /**
     * This method calls {@link TourDao#getTourById(int)}
     * @param id - id of the tour
     * @return object of the tour with id <b>id</b> from database if it exist
     */
    Tour getTourById(int id);

    /**
     * This method calls {@link TourDao#getAllTours()}
     * @return list of all tours from database
     */
    List<Tour> getAllTours();
}
