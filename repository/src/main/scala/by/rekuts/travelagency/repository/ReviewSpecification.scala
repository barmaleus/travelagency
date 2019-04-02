package by.rekuts.travelagency.repository

import java.util

import by.rekuts.travelagency.domain.Review
import javax.persistence.criteria.{CriteriaBuilder, Predicate, Root}

class ReviewSpecification extends Specification[Review] {
  def this(id: Int) {
    this()
    this.id = id
  }

  var userId: Int = _
  var tourId: Int = _

  override def getPredicates(root: Root[Review], builder: CriteriaBuilder): util.List[Predicate] = {
    var predicates = super.getPredicates(root, builder)
    if (userId != 0) {
      predicates.add(
        builder.equal(root.get("user"), userId)
      )
    }
    if (tourId != 0) {
      predicates.add(
        builder.equal(root.get("tour"), tourId)
      )
    }
    predicates
  }
}
