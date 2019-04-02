package by.rekuts.travelagency.repository

import java.time.LocalDate
import java.util

import by.rekuts.travelagency.domain.TourType.TourType
import by.rekuts.travelagency.domain.{Hotel, Tour}
import javax.persistence.criteria.{CriteriaBuilder, Join, Predicate, Root}

class TourSpecification extends Specification[Tour] {
  def this(id: Int) {
    this()
    this.id = id
  }

  var countryId: Int = _
  var date: LocalDate = _
  var duration: Int = _
  var minCost: BigDecimal = _
  var maxCost: BigDecimal = _
  var tourType: TourType = _
  var stars: Int = _
  var userId: Int = _

  override def getPredicates(root: Root[Tour], builder: CriteriaBuilder): util.List[Predicate] = {
    var predicates = super.getPredicates(root, builder)
    if (countryId != 0) {
      predicates.add(
        builder.equal(root.get("country"), countryId)
      )
    }
    if (date != null) {
      predicates.add(
        builder.equal(root.get("date"), date)
      )
    }
    if (duration != 0) {
      predicates.add(
        builder.equal(root.get("duration"), duration)
      )
    }
    if (minCost != null) {
      predicates.add(
        builder.ge(root.get("cost"), minCost)
      )
    }
    if (maxCost != null) {
      predicates.add(
        builder.le(root.get("cost"), maxCost)
      )
    }
    if (tourType != null) {
      predicates.add(
        builder.equal(root.get("tourType"), tourType)
      )
    }
    if (stars != 0) {
      val hotelJoin: Join[Tour, Hotel] = root.join("hotel")
      predicates.add(
        builder.equal(hotelJoin.get("stars"), stars)
      )
    }
    if (userId != 0) {
      predicates.add(
        builder.equal(root.join("users"), userId)
      )
    }
    predicates
  }
}
