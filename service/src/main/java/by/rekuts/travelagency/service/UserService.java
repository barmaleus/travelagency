package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.service.impl.CrudService;

/**
 * Interface UserService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of UserRepository interface and could call its methods.
 */
public interface UserService extends CrudService<User> {

    /**
     * It adds tour to favorites (List of tours in User class)
     * @param user - changed user
     * @param tour - tour, added to favorites
     */
    void addTourToFavorites(User user, Tour tour);

    /**
     * It removes tour from favorites tours list (List of tours in User class)
     * @param user - changed user
     * @param tour - tour, removed from favorites
     */
    void removeTourFromFavorites(User user, Tour tour);
}
