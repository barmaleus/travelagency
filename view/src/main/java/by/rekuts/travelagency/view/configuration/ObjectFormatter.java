package by.rekuts.travelagency.view.configuration;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.CountryService;
import by.rekuts.travelagency.service.HotelService;
import by.rekuts.travelagency.service.TourService;
import by.rekuts.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ObjectFormatter {

    @Autowired
    private TourService tourService;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private HotelService hotelService;

    Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) throws ParseException {
                return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                return DateTimeFormatter.ISO_DATE.format(object);
            }
        };
    }

    Formatter<Tour> tourFormatter() {
        return new Formatter<Tour>() {
            @Override
            public Tour parse(String tourId, Locale locale) throws ParseException {
                int id = Integer.parseInt(tourId);
                return tourService.getList(new TourSpecification(id)).get(0);
            }

            @Override
            public String print(Tour tour, Locale locale) {
                return tour.toString();
            }
        };
    }

    Formatter<User> userFormatter() {
        return new Formatter<User>() {
            @Override
            public User parse(String userId, Locale locale) throws ParseException {
                int id = Integer.parseInt(userId);
                return userService.getList(new UserSpecification(id)).get(0);
            }

            @Override
            public String print(User user, Locale locale) {
                return user.toString();
            }
        };
    }

    Formatter<Country> countryFormatter() {
        return new Formatter<Country>() {
            @Override
            public Country parse(String countryId, Locale locale) throws ParseException {
                int id = Integer.parseInt(countryId);
                return countryService.getList(new CountrySpecification(id)).get(0);
            }

            @Override
            public String print(Country country, Locale locale) {
                return country.toString();
            }
        };
    }

    Formatter<Hotel> hotelFormatter() {
        return new Formatter<Hotel>() {
            @Override
            public Hotel parse(String hotelId, Locale locale) throws ParseException {
                int id = Integer.parseInt(hotelId);
                return hotelService.getList(new HotelSpecification(id)).get(0);
            }

            @Override
            public String print(Hotel hotel, Locale locale) {
                return hotel.toString();
            }
        };
    }
}
