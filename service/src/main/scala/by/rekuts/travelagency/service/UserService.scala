package by.rekuts.travelagency.service

import by.rekuts.travelagency.domain.{Tour, User}

/**
  * Trait UserService concerns to Service layer.
  * Realisations of this trait has access to DAO layer
  * in the face of UserRepository trait and could call its methods.
  */
trait UserService extends CrudService[User] {
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
