package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;

import java.util.List;


/**
 * Interface UserService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with User objects in database.
 */
public interface UserRepository {

	/**
	 * This method inserts object of User class to database
	 * @param user - object of user
	 * @see User
	 */
	void insert(User user);

	/**
	 * This method deletes user with id <b>id</b> from database
	 * @param id - id of the user
	 * @see User#id
	 */
	void delete(int id);

	/**
	 * @return list of all users from database, or list with one user by id
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
