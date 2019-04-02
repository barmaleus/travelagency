package by.rekuts.travelagency.service

import by.rekuts.travelagency.domain.Review

/**
  * Trait ReviewService concerns to Service layer.
  * Realisations of this trait has access to DAO layer
  * in the face of ReviewRepository trait and could call its methods.
  */
trait ReviewService extends CrudService[Review]
