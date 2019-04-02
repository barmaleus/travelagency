package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.Hotel

/**
  * Trait HotelService concerns to DAO layer .
  * Realisations of this trait do some CRUD operations with Hotel objects in database.
  */
trait HotelRepository extends CrudRepository[Hotel]
