package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.dao.subjects.User;

import java.util.List;

public interface UserDao {
	void insert(User user);
	void delete(int id);
	User getUserById(int id);
	List<User> getAllUsers();
}
