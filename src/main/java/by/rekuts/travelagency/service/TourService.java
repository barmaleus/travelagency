package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Tour;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    /**
     * This method calls {@link TourDao#getToursByCriteria(Country, LocalDate, Integer, Tour.TourType, BigDecimal, BigDecimal, Integer)}
     * @param country - object of country
     * @param date - date in LocalDate format
     * @param duration - duration of tour, days
     * @param tourType - type of travel
     * @param minCost - lower bound of price
     * @param maxCost - upper bound of price
     * @param stars - hotel stars
     * @return - list of tours by specified parameters
     */
    List<Tour> getToursByCriteria(Country country, LocalDate date, Integer duration,
                                  Tour.TourType tourType, BigDecimal minCost, BigDecimal maxCost, Integer stars);

    /**
     * This method calls {@link TourDao#getToursByUserId(int)}
     * @return list of tours from database for a specified user by his id
     */
    List<Tour> getToursByUserId(int userId);
}
