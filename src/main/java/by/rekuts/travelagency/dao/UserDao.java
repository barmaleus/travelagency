package by.rekuts.travelagency.dao;

import java.util.List;
import java.util.Map;

import by.rekuts.travelagency.dao.subjects.User;

public interface UserDao {
	void insert(User user);
	void delete(int id);
	User getUserById(int id);
	List<User> getAllUsers();
}
