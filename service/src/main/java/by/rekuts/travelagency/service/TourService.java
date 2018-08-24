package by.rekuts.travelagency.service;

import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.domain.Tour;

import java.util.List;

/**
 * Interface TourService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of TourRepository interface and could call its methods.
 */
public interface TourService {

    /**
     * This method calls {@link TourRepository#insert(Tour)}
     * It inserts object of Tour class to database
     * @param tour - object of the tour
     * @see Tour
     */
    void insert(Tour tour);

    /**
     * This method calls {@link TourRepository#delete(int)}
     * It deletes tour with id <b>id</b> from database
     * @param id - id of the tour
     * @see Tour#id
     */
    void delete(int id);

    /**
     * This method calls {@link TourRepository#getList(Specification)}
     * @return list of tours from database by specification (tour id, country id, date, duration,
     * minimal cost or maximal cost, type of tour, amount of stars of tour hotel, user id)
     */
    List<Tour> getList(Specification specification);

    /**
     * @param csvFileContent - path to .csv file with tours.
     */
    void importCsvTours(String csvFileContent);
}
