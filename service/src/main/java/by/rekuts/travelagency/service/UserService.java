package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.UserRepository;
import by.rekuts.travelagency.domain.User;

import java.util.List;

/**
 * Interface UserService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of UserRepository interface and could call its methods.
 */
public interface UserService {

    /**
     * This method calls {@link UserRepository#insert(User)}
     * It inserts object of User class to database
     * @param user - object of user
     * @see User
     */
    void insert(User user);

    /**
     * This method calls {@link UserRepository#delete(int)}
     * It deletes user with id <b>id</b> from database
     * @param id - id of the user
     * @see User#id
     */
    void delete(int id);

    /**
     * This method calls {@link UserRepository#getList(Specification)}
     * @return list of all users from database or list with one user by id usind specification
     */
    List<User> getList(Specification specification);

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
