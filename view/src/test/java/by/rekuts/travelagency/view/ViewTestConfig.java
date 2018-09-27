package by.rekuts.travelagency.view;

import by.rekuts.travelagency.service.*;
import by.rekuts.travelagency.view.controller.CreateController;
import by.rekuts.travelagency.view.controller.GetController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
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
    public GetController mainController() {
        return new GetController();
    }

    @Bean
    public CreateController createController() {
        return new CreateController();
    }
}
