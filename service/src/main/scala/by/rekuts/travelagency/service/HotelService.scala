package by.rekuts.travelagency.service

import by.rekuts.travelagency.domain.Hotel

/**
  * Trait HotelService concerns to Service layer.
  * Realisations of this trait has access to DAO layer
  * in the face of HotelRepository trait and could call its methods.
  */
trait HotelService extends CrudService[Hotel]
