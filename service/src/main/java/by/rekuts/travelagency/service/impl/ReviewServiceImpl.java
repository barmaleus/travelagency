package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.repository.ReviewRepository;
import by.rekuts.travelagency.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public void insert(Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    @Override
    public void update(Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    @Override
    public void delete(int id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Review> getList(Specification<Review> specification) {
        return reviewRepository.findAll(specification);
    }
}
