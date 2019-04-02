package by.rekuts.travelagency.repository

trait CSVTourLoader {
  /**
    * @param csvFileContent - path to .csv file with tours.
    */
  def importCsvTours(csvFileContent: String): Unit
}
