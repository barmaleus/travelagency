package by.rekuts.travelagency.repository.impl

import java.util

import by.rekuts.travelagency.domain.Tour
import by.rekuts.travelagency.repository.{Specification, TourRepository, TourSpecification}
import javax.persistence.criteria.{CriteriaBuilder, CriteriaQuery, Predicate, Root}
import org.springframework.stereotype.Repository

@Repository
class TourRepositoryImpl extends TourRepository {

  override def update(tour: Tour): Unit = {
    val dbTour = entityManager.find(classOf[Tour], tour.id)
    dbTour.photo_$eq(tour.photo)
    dbTour.date_$eq(tour.date)
    dbTour.duration_$eq(tour.duration)
    dbTour.description_$eq(tour.description)
    dbTour.cost_$eq(tour.cost)
    dbTour.tourType_$eq(tour.tourType)
    dbTour.hotel_$eq(tour.hotel)
    dbTour.country_$eq(tour.country)
    dbTour.users_$eq(tour.users)
    entityManager.merge(dbTour)
  }

  override def delete(id: Int): Unit = entityManager.remove(entityManager.find(classOf[Tour], id))

  override def getList(specification: Specification[_]): util.List[Tour] = {
    val tourSpecification: TourSpecification = specification.asInstanceOf[TourSpecification]
    val builder: CriteriaBuilder = entityManager.getCriteriaBuilder
    val criteriaQuery: CriteriaQuery[Tour] = builder.createQuery(classOf[Tour])
    val root: Root[Tour] = criteriaQuery.from(classOf[Tour])
    val predicates = tourSpecification.getPredicates(root, builder)
    if (!predicates.isEmpty) {
      criteriaQuery.where(predicates.toArray[Predicate](new Array[Predicate](predicates.size())): _*)
    }
    entityManager.createQuery(criteriaQuery).getResultList
  }
}
