package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Hotel;

/**
 * Interface HotelService concerns to DAO layer .
 * Realisations of this interface do some CRUD operations with Hotel objects in database.
 */
public interface HotelRepository extends CrudRepository<Hotel> {
}
