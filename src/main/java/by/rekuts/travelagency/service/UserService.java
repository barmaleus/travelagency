package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    void delete(int id);
    User getUserById(int id);
    List<User> getAllUsers();
}
