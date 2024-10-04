package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Review;

/**
 * Interface ReviewService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of ReviewRepository interface and could call its methods.
 */
public interface ReviewService extends CrudService<Review> {
}
