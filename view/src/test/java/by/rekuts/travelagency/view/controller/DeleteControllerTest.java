package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.TourService;
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

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ViewTestConfig.class, TestSecurityConfiguration.class})
@WebAppConfiguration
@ComponentScan(basePackages = {"by.rekuts.**"})
public class DeleteControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private TourService tourService;

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
    public void delReviewTest() throws Exception {
        mvc.perform(post("/del-review")
                .param("reviewId", "123")
                .with(user("user")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void delReviewTestFalse1() throws Exception {
        mvc.perform(post("/del-review")
                .param("reviewId", "123")
                .with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void delReviewTestFalse2() throws Exception {
        mvc.perform(post("/del-review")
                .param("reviewId", "review")
                .with(user("user")))
                .andExpect(status().isBadRequest());
    }

    //todo test works alone but doesn't work while maven compiling
//    @Test
//    public void delCountryTest() throws Exception {
//        mvc.perform(post("/del-country")
//                .param("countryId", "123")
//                .with(user("user").roles("ADMIN")))
//                .andExpect(status().isFound())
//                .andExpect(view().name("redirect:/countries"));
//    }

    @Test
    public void delCountryTestFalse1() throws Exception {
        Country country = new Country();
        country.setId(123);
        Tour tour = new Tour();
        tour.setCountry(country);
        List<Tour> tours = Collections.singletonList(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(post("/del-country")
                .param("countryId", "123")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delCountryTestFalse2() throws Exception {
        mvc.perform(post("/del-country")
                .param("countryId", "123")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void delCountryTestFalse3() throws Exception {
        mvc.perform(post("/del-country")
                .param("countryId", "UAE")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delHotelTest() throws Exception {
        mvc.perform(post("/del-hotel")
                .param("hotelId", "555")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/hotels"));
    }

    @Test
    public void delHotelsTestFalse1() throws Exception {
        Hotel hotel = new Hotel();
        hotel.setId(123);
        Tour tour = new Tour();
        tour.setHotel(hotel);
        List<Tour> tours = Collections.singletonList(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(post("/del-hotel")
                .param("hotelId", "123")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delHotelTestFalse2() throws Exception {
        mvc.perform(post("/del-hotel")
                .param("hotelId", "123")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void delHotelTestFalse3() throws Exception {
        mvc.perform(post("/del-hotel")
                .param("hotelId", "Tourist")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delTourTest() throws Exception {
        mvc.perform(post("/del-tour")
                .param("tourId", "123")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void delTourTestFalse1() throws Exception {
        mvc.perform(post("/del-tour")
                .param("tourId", "123")
                .with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void delTourTestFalse2() throws Exception {
        mvc.perform(post("/del-tour")
                .param("tourId", "tour")
                .with(user("user")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delUserTest() throws Exception {
        mvc.perform(post("/del-user")
                .param("userId", "123")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    public void delUserTestFalse1() throws Exception {
        mvc.perform(post("/del-user")
                .param("userId", "123")
                .with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void delUserTestFalse2() throws Exception {
        mvc.perform(post("/del-user")
                .param("userId", "user")
                .with(user("user")))
                .andExpect(status().isBadRequest());
    }
}
