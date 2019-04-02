package by.rekuts.travelagency.repository.impl

import java.util

import by.rekuts.travelagency.domain.Country
import by.rekuts.travelagency.repository.{CountryRepository, CountrySpecification, Specification}
import javax.persistence.criteria.Predicate
import org.springframework.stereotype.Repository

@Repository
class CountryRepositoryImpl extends CountryRepository {

  override def update(country: Country): Unit = {
    val dbCountry = entityManager.find(classOf[Country], country.id)
    dbCountry.name_$eq(country.name)
    entityManager.merge(dbCountry)
  }

  override def delete(id: Int): Unit = entityManager.remove(entityManager.find(classOf[Country], id))

  override def getList(specification: Specification[_]): util.List[Country] = {
    val countrySpecification = specification.asInstanceOf[CountrySpecification]
    val builder = entityManager.getCriteriaBuilder
    val criteriaQuery = builder.createQuery(classOf[Country])
    val countryRoot = criteriaQuery.from(classOf[Country])
    val predicates = countrySpecification.getPredicates(countryRoot, builder)
    if (!predicates.isEmpty) {
      criteriaQuery.where(predicates.toArray[Predicate](new Array[Predicate](predicates.size())): _*)
    }
    entityManager.createQuery(criteriaQuery).getResultList
  }
}
