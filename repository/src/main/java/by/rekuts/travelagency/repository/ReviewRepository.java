package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface ReviewService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Review objects in database.
 */
public interface ReviewRepository extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review> {
}
