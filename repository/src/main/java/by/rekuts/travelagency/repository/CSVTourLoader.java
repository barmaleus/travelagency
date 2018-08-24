package by.rekuts.travelagency.repository;

public interface CSVTourLoader {
    /**
     * @param csvFileContent - path to .csv file with tours.
     */
    void importCsvTours(String csvFileContent);
}
