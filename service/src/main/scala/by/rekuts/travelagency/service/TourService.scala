package by.rekuts.travelagency.service

import by.rekuts.travelagency.domain.Tour

/**
  * Trait TourService concerns to Service layer.
  * Realisations of this trait has access to DAO layer
  * in the face of TourRepository trait and could call its methods.
  */
trait TourService extends CrudService[Tour] {
  /**
    * @param csvFileContent - path to .csv file with tours.
    */
  def importCsvTours(csvFileContent: String): Unit
}
