package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.subjects.User;

import java.util.List;


/**
 * Interface UserService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with User objects in database.
 */
public interface UserDao {

	/**
	 * This method inserts object of User class to database
	 * @param user - object of user
	 * @see User
	 */
	void insert(User user);

	/**
	 * This method deletes user with id <b>id</b> from database
	 * @param id - id of the user
	 * @see User#userId
	 */
	void delete(int id);

	/**
	 * @param id - id of the user
	 * @return object of the user with id <b>id</b> from database if it exist
	 */
	User getUserById(int id);

	/**
	 * @return list of all users from database
	 */
	List<User> getAllUsers();
}
