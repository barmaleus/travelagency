package by.rekuts.travelagency.repository

import java.util

import javax.persistence.criteria.{CriteriaBuilder, Predicate, Root}

trait Specification[T] {

  var id: Int = _

  def getPredicates(root: Root[T], builder: CriteriaBuilder): util.List[Predicate] = {
    var predicates = new util.ArrayList[Predicate]
    if (id != 0) {
      predicates.add(
        builder.equal(root.get("id"), id)
      )
    }
    predicates
  }
}
