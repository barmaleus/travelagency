package by.rekuts.travelagency.repository.impl

import java.util

import by.rekuts.travelagency.domain.{Tour, User, UserRole}
import by.rekuts.travelagency.repository.{Specification, UserRepository, UserSpecification}
import javax.persistence.criteria.{CriteriaBuilder, CriteriaQuery, Predicate, Root}
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl extends UserRepository {

  override def insert(user: User): Unit = {
    if(user.role == null) {
      user.role_$eq(UserRole.ROLE_MEMBER)
    }
    entityManager.persist(user)
  }

  override def update(user: User): Unit = {
    val dbUser = entityManager.find(classOf[User], user.id)
    dbUser.login_$eq(user.login)
    dbUser.password_$eq(user.password)
    dbUser.role_$eq(user.role)
    entityManager.merge(dbUser)
  }

  override def delete(id: Int): Unit = entityManager.remove(entityManager.find(classOf[User], id))

  override def getList(specification: Specification[_]): util.List[User] = {
    val reviewSpecification: UserSpecification = specification.asInstanceOf[UserSpecification]
    val builder: CriteriaBuilder = entityManager.getCriteriaBuilder
    val criteriaQuery: CriteriaQuery[User] = builder.createQuery(classOf[User])
    val root: Root[User] = criteriaQuery.from(classOf[User])
    val predicates = reviewSpecification.getPredicates(root, builder)
    if (!predicates.isEmpty) {
      criteriaQuery.where(predicates.toArray[Predicate](new Array[Predicate](predicates.size())): _*)
    }
    entityManager.createQuery(criteriaQuery).getResultList
  }

  override def addTourToFavorites(user: User, tour: Tour): Unit = {
    val tours = user.tours
    if (!tours.contains(tour)) tours.add(tour)
    user.tours_$eq(tours)
    entityManager.merge(user)
  }

  override def removeTourFromFavorites(user: User, tour: Tour): Unit = {
    val tours = user.tours
    tours.remove(tour)
    user.tours_$eq(tours)
    entityManager.merge(user)
  }
}
