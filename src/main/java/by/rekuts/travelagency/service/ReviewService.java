package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Review;

import java.util.List;

public interface ReviewService {
    void insert(Review review);
    void delete(int id);
    Review getReviewById(int id);
    List<Review> getAllReviews();
}
