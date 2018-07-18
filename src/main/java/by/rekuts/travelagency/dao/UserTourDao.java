package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Tour;
import by.rekuts.travelagency.dao.subjects.User;
import by.rekuts.travelagency.dao.subjects.UserTour;

public interface UserTourDao {
	void insert(UserTour userTour);
	void delete(int userId, int tourId);
	List<User> getUsersByTourId(int tourId);
	List<Tour> getToursByUserId(int userId);
}
