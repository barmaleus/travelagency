package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.repository.ReviewRepository;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public void insert(Review review) {
        reviewRepository.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewRepository.delete(id);
    }

    @Override
    public List<Review> getList(Specification specification) {
        return reviewRepository.getList(specification);
    }
}
