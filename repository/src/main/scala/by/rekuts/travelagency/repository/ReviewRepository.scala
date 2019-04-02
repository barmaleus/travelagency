package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.Review

/**
  * Trait ReviewService concerns to DAO layer.
  * Realisations of this trait do some CRUD operations with Review objects in database.
  */
trait ReviewRepository extends CrudRepository[Review]