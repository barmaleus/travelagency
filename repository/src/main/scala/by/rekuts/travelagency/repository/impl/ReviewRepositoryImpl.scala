package by.rekuts.travelagency.repository.impl

import java.util

import by.rekuts.travelagency.domain.Review
import by.rekuts.travelagency.repository.{ReviewRepository, ReviewSpecification, Specification}
import javax.persistence.criteria.{CriteriaBuilder, CriteriaQuery, Predicate, Root}
import org.springframework.stereotype.Repository

@Repository
class ReviewRepositoryImpl extends ReviewRepository {

  override def update(review: Review): Unit = {
    val dbReview: Review = entityManager.find(classOf[Review], review.id)
    dbReview.text_$eq(review.text)
    dbReview.user_$eq(review.user)
    dbReview.tour_$eq(review.tour)
    entityManager.merge(dbReview)
  }

  override def delete(id: Int): Unit = entityManager.remove(entityManager.find(classOf[Review], id))

  override def getList(specification: Specification[_]): util.List[Review] = {
    val reviewSpecification: ReviewSpecification = specification.asInstanceOf[ReviewSpecification]
    val builder: CriteriaBuilder = entityManager.getCriteriaBuilder
    val criteriaQuery: CriteriaQuery[Review] = builder.createQuery(classOf[Review])
    val root: Root[Review] = criteriaQuery.from(classOf[Review])
    val predicates = reviewSpecification.getPredicates(root, builder)
    if (!predicates.isEmpty) {
      criteriaQuery.where(predicates.toArray[Predicate](new Array[Predicate](predicates.size())): _*)
    }
    entityManager.createQuery(criteriaQuery).getResultList
  }
}
