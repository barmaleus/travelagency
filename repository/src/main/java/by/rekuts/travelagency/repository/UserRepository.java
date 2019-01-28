package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;


/**
 * Interface UserService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with User objects in database.
 */
public interface UserRepository extends CrudRepository<User> {

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
