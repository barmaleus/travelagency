package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.Tour

/**
  * Trait TourService concerns to DAO layer.
  * Realisations of this trait do some CRUD operations with Tour objects in database.
  */
trait TourRepository extends CrudRepository[Tour]