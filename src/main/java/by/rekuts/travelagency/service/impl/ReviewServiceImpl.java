package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.ReviewDao;
import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Override
    public void insert(Review review) {
        reviewDao.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewDao.delete(id);
    }

    @Override
    public Review getReviewById(int id) {
        return reviewDao.getReviewById(id);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    @Override
    public List<Review> getReviewsByUserId(int userId) {
        return reviewDao.getReviewsByUserId(userId);
    }

    @Override
    public List<Review> getReviewsByTourId(int tourId) {
        return reviewDao.getReviewsByTourId(tourId);
    }
}
