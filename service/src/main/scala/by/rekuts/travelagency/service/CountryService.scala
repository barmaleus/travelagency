package by.rekuts.travelagency.service

import by.rekuts.travelagency.domain.Country

/**
  * Trait CountryService concerns to Service layer.
  * Realisations of this trait has access to DAO layer
  * in the face of CountryRepository trait and could call its methods.
  */
trait CountryService extends CrudService[Country]
