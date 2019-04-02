package by.rekuts.travelagency.repository.impl

import java.util

import by.rekuts.travelagency.domain.Hotel
import by.rekuts.travelagency.repository.{HotelRepository, HotelSpecification, Specification}
import javax.persistence.criteria.Predicate
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.stereotype.Repository

@Repository
class HotelRepositoryImpl extends HotelRepository {

  override def update(hotel: Hotel): Unit = {
    val dbHotel = entityManager.find(classOf[Hotel], hotel.id)
    dbHotel.name_$eq(hotel.name)
    dbHotel.stars_$eq(hotel.stars)
    dbHotel.website_$eq(hotel.website)
    dbHotel.latitude_$eq(hotel.latitude)
    dbHotel.longitude_$eq(hotel.longitude)
    dbHotel.features_$eq(hotel.features)
    entityManager.merge(dbHotel)
  }

  override def delete(id: Int): Unit = {
    val query = entityManager.createQuery("SELECT t.id FROM Tour AS t WHERE hotel_id = ?1", classOf[Integer])
    query.setParameter(1, id)
    val results = query.getResultList
    if (results == null || results.isEmpty) {
      entityManager.remove(entityManager.find(classOf[Hotel], id))
      HotelRepositoryImpl.log.info("Hotel deleted")
    }
    else HotelRepositoryImpl.log.info("Can't delete hotel. There are some foreign keys references on it.")
  }

  override def getList(specification: Specification[_]): util.List[Hotel] = {
    val hotelSpecification = specification.asInstanceOf[HotelSpecification]
    val builder = entityManager.getCriteriaBuilder
    val criteriaQuery = builder.createQuery(classOf[Hotel])
    val root = criteriaQuery.from(classOf[Hotel])
    val predicates = hotelSpecification.getPredicates(root, builder)
    if (!predicates.isEmpty) {
      criteriaQuery.where(predicates.toArray[Predicate](new Array[Predicate](predicates.size())): _*)
    }
    entityManager.createQuery(criteriaQuery).getResultList
  }
}

object HotelRepositoryImpl {
  val log: Logger = LoggerFactory.getLogger(classOf[HotelRepositoryImpl])
}
