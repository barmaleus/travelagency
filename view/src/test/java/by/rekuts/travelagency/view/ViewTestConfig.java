package by.rekuts.travelagency.view;

import by.rekuts.travelagency.service.*;
import by.rekuts.travelagency.view.controller.MainController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewTestConfig {
    @Bean
    public TourService tourService() {
        return Mockito.mock(TourService.class);
    }

    @Bean
    public CountryService countryService() {
        return Mockito.mock(CountryService.class);
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public ReviewService reviewService() {
        return Mockito.mock(ReviewService.class);
    }

    @Bean
    public HotelService hotelService() {
        return Mockito.mock(HotelService.class);
    }

    @Bean
    public MainController mainController() {
        return new MainController();
    }
}
