package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Tour;

/**
 * Interface TourService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Tour objects in database.
 */
public interface TourRepository extends CrudRepository<Tour> {
}
