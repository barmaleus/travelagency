package by.rekuts.travelagency.dao;

import java.util.List;

import by.rekuts.travelagency.dao.subjects.Review;

public interface ReviewDao {
	void insert(Review review);
	void delete(int id);
	Review getReviewById(int id);
	List<Review> getAllReviews();
}
