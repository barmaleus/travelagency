package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.service.*;
import by.rekuts.travelagency.view.confuguration.TestSecurityConfiguration;
import by.rekuts.travelagency.view.confuguration.ViewTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ViewTestConfig.class, TestSecurityConfiguration.class})
@WebAppConfiguration
@ComponentScan(basePackages = {"by.rekuts.**"})
public class UpdateControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CountryService countryService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private TourService tourService;
    @Autowired
    private UserService userService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @Test
    public void updateCountryTest_post() throws Exception {
        mvc.perform(post("/update-country")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/countries"));
    }

    @Test
    public void updateCountryTestFalse_post() throws Exception {
        mvc.perform(post("/update-country")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateCountryTest_get() throws Exception {
        var country = new Country();
        country.setId(123);
        var countries = List.of(country);
        when(countryService.getList(any(Specification.class))).thenReturn(countries);
        mvc.perform(get("/countries/{countryId}/update", 123)
                .with(user("user").roles("ADMIN")))
                .andExpect(view().name("update/update-country"));
    }

    @Test
    public void updateCountryTestFalse1_get() throws Exception {
        var country = new Country();
        country.setId(123);
        var countries = List.of(country);
        when(countryService.getList(any(Specification.class))).thenReturn(countries);
        mvc.perform(get("/countries/{countryId}/update", 123)
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateCountryTestFalse2_get() throws Exception {
        var country = new Country();
        country.setId(123);
        var countries = List.of(country);
        when(countryService.getList(any(Specification.class))).thenReturn(countries);
        mvc.perform(get("/countries/{countryId}/update", "t34")
                .with(user("user")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateHotelTest_post() throws Exception {
        mvc.perform(post("/update-hotel")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/hotels"));
    }

    @Test
    public void updateHotelTestFalse_post() throws Exception {
        mvc.perform(post("/update-hotel")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateHotelTest_get() throws Exception {
        var hotel = new Hotel();
        hotel.setId(123);
        var hotels = List.of(hotel);
        when(hotelService.getList(any(Specification.class))).thenReturn(hotels);
        mvc.perform(get("/hotels/{hotelId}/update", 123)
                .with(user("user").roles("ADMIN")))
                .andExpect(view().name("update/update-hotel"));
    }

    @Test
    public void updateHotelTestFalse1_get() throws Exception {
        var hotel = new Hotel();
        hotel.setId(123);
        var hotels = List.of(hotel);
        when(hotelService.getList(any(Specification.class))).thenReturn(hotels);
        mvc.perform(get("/hotels/{hotelId}/update", 123)
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateHotelTestFalse2_get() throws Exception {
        var hotel = new Hotel();
        hotel.setId(123);
        var hotels = List.of(hotel);
        when(hotelService.getList(any(Specification.class))).thenReturn(hotels);
        mvc.perform(get("/hotels/{hotelId}/update", "t34")
                .with(user("user")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateReviewTest_post() throws Exception {
        mvc.perform(post("/update-review")
                .with(user("user")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/reviews"));
    }

    @Test
    public void updateReviewTestFalse_post() throws Exception {
        mvc.perform(post("/update-review")
                .with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void updateReviewTest_get() throws Exception {
        var review = new Review();
        review.setId(123);
        var reviews = List.of(review);
        when(reviewService.getList(any(Specification.class))).thenReturn(reviews);
        mvc.perform(get("/reviews/{reviewId}/update", 123)
                .with(user("user")))
                .andExpect(view().name("update/update-review"));
    }

    @Test
    public void updateReviewTestFalse1_get() throws Exception {
        var review = new Review();
        review.setId(123);
        var reviews = List.of(review);
        when(reviewService.getList(any(Specification.class))).thenReturn(reviews);
        mvc.perform(get("/reviews/{reviewId}/update", 123)
                .with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void updateReviewTestFalse2_get() throws Exception {
        var review = new Review();
        review.setId(123);
        var reviews = List.of(review);
        when(reviewService.getList(any(Specification.class))).thenReturn(reviews);
        mvc.perform(get("/countries/{countryId}/update", "t34")
                .with(user("user")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTourTest_post() throws Exception {
        mvc.perform(post("/update-tour")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void updateTourTestFalse_post() throws Exception {
        mvc.perform(post("/update-tour")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateTourTest_get() throws Exception {
        var tour = new Tour();
        tour.setId(123);
        var tours = List.of(tour);
        when(tourService.getList(any(Specification.class))).thenReturn(tours);
        mvc.perform(get("/tours/{tourId}/update", 123)
                .with(user("user").roles("ADMIN")))
                .andExpect(view().name("update/update-tour"));
    }

    @Test
    public void updateTourTestFalse1_get() throws Exception {
        var tour = new Tour();
        tour.setId(123);
        var tours = List.of(tour);
        when(tourService.getList(any(Specification.class))).thenReturn(tours);
        mvc.perform(get("/tours/{tourId}/update", 123)
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateTourTestFalse2_get() throws Exception {
        var tour = new Tour();
        tour.setId(123);
        var tours = List.of(tour);
        when(tourService.getList(any(Specification.class))).thenReturn(tours);
        mvc.perform(get("/tours/{tourId}/update", "t34")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserTest_post() throws Exception {
        mvc.perform(post("/update-user")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    public void updateUserTestFalse_post() throws Exception {
        mvc.perform(post("/update-tour")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateUserTest_get() throws Exception {
        var user = new User();
        user.setId(123);
        var users = List.of(user);
        when(userService.getList(any(Specification.class))).thenReturn(users);
        mvc.perform(get("/users/{userId}/update", 123)
                .with(user("user").roles("ADMIN")))
                .andExpect(view().name("update/update-user"));
    }

    @Test
    public void updateUserTestFalse1_get() throws Exception {
        var user = new User();
        user.setId(123);
        var users = List.of(user);
        when(userService.getList(any(Specification.class))).thenReturn(users);
        mvc.perform(get("/users/{userId}/update", 123)
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void updateUserTestFalse2_get() throws Exception {
        var user = new User();
        user.setId(123);
        var users = List.of(user);
        when(userService.getList(any(Specification.class))).thenReturn(users);
        mvc.perform(get("/users/{userId}/update", "t34")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }
}
