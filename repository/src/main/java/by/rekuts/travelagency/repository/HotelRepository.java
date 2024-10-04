package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface HotelService concerns to DAO layer .
 * Realisations of this interface do some CRUD operations with Hotel objects in database.
 */
public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel> {
}
