package by.rekuts.travelagency.logic.service.impl;

import by.rekuts.travelagency.logic.repository.ReviewRepository;
import by.rekuts.travelagency.logic.repository.Specification;
import by.rekuts.travelagency.logic.domain.Review;
import by.rekuts.travelagency.logic.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Transactional
    @Override
    public void insert(Review review) {
        reviewRepository.insert(review);
    }

    @Transactional
    @Override
    public void delete(int id) {
        reviewRepository.delete(id);
    }

    @Transactional
    @Override
    public List<Review> getList(Specification specification) {
        return reviewRepository.getList(specification);
    }
}
