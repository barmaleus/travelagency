package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.dao.ReviewDao;
import by.rekuts.travelagency.dao.subjects.Review;
import by.rekuts.travelagency.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
