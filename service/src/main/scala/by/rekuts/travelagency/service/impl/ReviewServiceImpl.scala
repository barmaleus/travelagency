package by.rekuts.travelagency.service.impl

import java.util

import by.rekuts.travelagency.domain.Review
import by.rekuts.travelagency.repository.{ReviewRepository, Specification}
import by.rekuts.travelagency.service.ReviewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewServiceImpl extends ReviewService {
  @Autowired
  private var reviewRepository: ReviewRepository = _

  @Transactional
  override def insert(review: Review): Unit = reviewRepository.insert(review)

  @Transactional
  override def update(review: Review): Unit = reviewRepository.update(review)

  @Transactional
  override def delete(id: Int): Unit = reviewRepository.delete(id)

  @Transactional
  override def getList(specification: Specification[_]): util.List[Review] = reviewRepository.getList(specification)
}
