package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.Country

/**
  * Trait CountryRepository concerns to DAO layer.
  * Realisations of this trait do some CRUD operations with Country objects in database.
  */
trait CountryRepository extends CrudRepository[Country]
