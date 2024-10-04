package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface TourService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Tour objects in database.
 */
public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {
}
