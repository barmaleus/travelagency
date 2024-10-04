package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.CsvTour;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.CSVTourLoader;
import by.rekuts.travelagency.repository.CountryRepository;
import by.rekuts.travelagency.repository.HotelRepository;
import by.rekuts.travelagency.repository.HotelSpecification;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Component
public class CSVTourLoaderImpl implements CSVTourLoader {

    @PersistenceContext
    private EntityManager entityManager;

    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;

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

    private List<CsvTour> importCsvToCsvTourList(String csvFileContent) throws IOException {

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
        Optional<Hotel> hotel = hotelRepository.findOne(hs);
        hotel.ifPresent(tour::setHotel);
        Optional<Country> country = countryRepository.findById(csvTour.getCountryId());
        country.ifPresent(tour::setCountry);
        return tour;
    }
}
