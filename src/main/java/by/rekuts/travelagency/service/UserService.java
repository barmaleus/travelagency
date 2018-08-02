package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.UserDao;
import by.rekuts.travelagency.domain.User;

import java.util.List;

/**
 * Interface UserService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of UserDao interface and could call its methods.
 */
public interface UserService {

    /**
     * This method calls {@link UserDao#insert(User)}
     * It inserts object of User class to database
     * @param user - object of user
     * @see User
     */
    void insert(User user);

    /**
     * This method calls {@link UserDao#delete(int)}
     * It deletes user with id <b>id</b> from database
     * @param id - id of the user
     * @see User#userId
     */
    void delete(int id);

    /**
     * This method calls {@link UserDao#getUserById(int)}
     * @param id - id of the user
     * @return object of the user with id <b>id</b> from database if it exist
     */
    User getUserById(int id);

    /**
     * This method calls {@link UserDao#getAllUsers()}
     * @return list of all users from database
     */
    List<User> getAllUsers();
}
