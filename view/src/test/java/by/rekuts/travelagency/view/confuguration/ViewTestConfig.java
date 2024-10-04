package by.rekuts.travelagency.view.confuguration;

import by.rekuts.travelagency.service.*;
import by.rekuts.travelagency.view.controller.*;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
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
    public CountryController countryController() {
        return new CountryController(countryService());
    }

    @Bean
    public HotelController hotelController() {
        return new HotelController(hotelService());
    }

    @Bean
    public ReviewController reviewController() {
        return new ReviewController(tourService(), userService(), reviewService());
    }

    @Bean
    public TourController tourController() {
        return new TourController(tourService(), countryService(), userService(), reviewService(), hotelService());
    }

    @Bean
    public UserController userController() {
        return new UserController(userService(), tourService(), reviewService());
    }

    @Bean
    public ImportController importController() {
        return new ImportController(tourService());
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
