package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.{Tour, User}

/**
  * Trait UserService concerns to DAO layer.
  * Realisations of this trait do some CRUD operations with User objects in database.
  */
trait UserRepository extends CrudRepository[User] {

  /**
    * It adds tour to favorites (List of tours in User class)
    *
    * @param user - changed user
    * @param tour - tour, added to favorites
    */
  def addTourToFavorites(user: User, tour: Tour): Unit

  /**
    * It removes tour from favorites tours list (List of tours in User class)
    *
    * @param user - changed user
    * @param tour - tour, removed from favorites
    */
  def removeTourFromFavorites(user: User, tour: Tour): Unit
}
