package by.rekuts.travelagency.repository.impl;

import java.io.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import by.rekuts.travelagency.domain.CsvTour;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Component
public class CSVTourLoaderImpl implements CSVTourLoader {

    @PersistenceContext
    private EntityManager entityManager;

    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public CSVTourLoaderImpl(CountryRepository countryRepository, HotelRepository hotelRepository) {
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void importCsvTours(String csvFilePath) {
        List<CsvTour> csvTours = null;
        try {
             csvTours = importCsvToCsvTourList(csvFilePath);
        } catch (IOException e) {
            log.warn("The csv file not found on path " + csvFilePath, e);
        }
       insertCsvToursToDatabase(csvTours);
    }

    private List<CsvTour> importCsvToCsvTourList(String csvFileContent) throws IOException{

        List<CsvTour> csvTours = new ArrayList<>();
        try (
                Reader reader = new StringReader(csvFileContent);
        ) {

            CsvToBean<CsvTour> csvToBean = new CsvToBeanBuilder<CsvTour>(reader)
                    .withType(CsvTour.class)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            for (CsvTour csvTour : csvToBean) {
                csvTours.add(csvTour);
            }
        }
        return csvTours;
    }

    private void insertCsvToursToDatabase(List<CsvTour> csvTours) {
        for (CsvTour csvTour : csvTours) {
            Tour tour = parseCsvTourToTour(csvTour);
            entityManager.persist(tour);
        }
    }

    private Tour parseCsvTourToTour(CsvTour csvTour) {
        Tour tour = new Tour();
        tour.setPhoto(csvTour.getPhoto());
        tour.setDate(csvTour.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        tour.setDuration(csvTour.getDuration());
        tour.setDescription(csvTour.getDescription());
        tour.setCost(csvTour.getCost());
        tour.setTourType(Tour.TourType.values()[csvTour.getTourTypeId()]);
        HotelSpecification hs = new HotelSpecification(csvTour.getHotelId());
        Hotel hotel = hotelRepository.getList(hs).get(0);
        tour.setHotel(hotel);
        tour.setCountry(countryRepository.getList(new CountrySpecification(csvTour.getCountryId())).get(0));
        return tour;
    }
}