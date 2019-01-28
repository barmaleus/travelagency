package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Review;

/**
 * Interface ReviewService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Review objects in database.
 */
public interface ReviewRepository extends CrudRepository<Review> {
}
