package by.rekuts.travelagency.repository

import java.util

import by.rekuts.travelagency.domain.User
import javax.persistence.criteria.{CriteriaBuilder, Predicate, Root}

class UserSpecification extends Specification[User] {
  def this(id: Int) {
    this()
    this.id = id
  }

  var login: String = _

  override def getPredicates(root: Root[User], builder: CriteriaBuilder): util.List[Predicate] = {
    var predicates = super.getPredicates(root, builder)
    if (login != null) {
      predicates.add(
        builder.equal(root.get("login"), login)
      )
    }
    predicates
  }
}
