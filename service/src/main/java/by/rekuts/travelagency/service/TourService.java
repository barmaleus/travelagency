package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.service.impl.CrudService;

/**
 * Interface TourService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of TourRepository interface and could call its methods.
 */
public interface TourService extends CrudService<Tour> {

    /**
     * @param csvFileContent - path to .csv file with tours.
     */
    void importCsvTours(String csvFileContent);
}
