package by.rekuts.travelagency.view.controller;

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
public class CreateControllerTest {
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
    public void signUpTest() throws Exception {
        mvc.perform(post("/reg-user").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void signUpTestFalse() throws Exception {
        mvc.perform(post("/reg-user").with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void regCountryTest() throws Exception {
        mvc.perform(post("/reg-country").with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/countries"));
    }

    @Test
    public void regCountryTestFalse() throws Exception {
        mvc.perform(post("/reg-country").with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void newReviewTest() throws Exception {
        var tour = new Tour();
        tour.setId(6);
        var tours = List.of(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).with(user("user").roles("ADMIN")))
                .andExpect(view().name("create/new-review"));
    }

    @Test
    public void newReviewTestFalse1() throws Exception {
        var tour = new Tour();
        tour.setId(6);
        var tours = List.of(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/new-review").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void newReviewTestFalse2() throws Exception {
        var tour = new Tour();
        tour.setId(6);
        var tours = List.of(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).with(user("user").roles("MEMBER")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void newUserReviewTestTrue1() throws Exception {
        mvc.perform(get("/tours/{tourId}/add-review", 6).sessionAttr("sesUserId", 4).with(user("user").roles("ADMIN")))
                .andExpect(view().name("create/new-review"));
    }

    @Test
    public void newUserReviewTestTrue2() throws Exception {
        mvc.perform(get("/tours/{tourId}/add-review", 6).sessionAttr("sesUserId", 4).with(user("user").roles("MEMBER")))
                .andExpect(view().name("create/new-review"));
    }

    @Test
    public void newUserReviewTestFalse() throws Exception {
        mvc.perform(get("/tours/{tourId}/add-review", 6).sessionAttr("sesUserId", 4).with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void regReviewTestTrue1() throws Exception {
        mvc.perform(post("/reg-review")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void regReviewTestTrue2() throws Exception {
        mvc.perform(post("/reg-review")
                .with(user("user")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void regReviewTestFalse1_anonymous() throws Exception {
        mvc.perform(post("/reg-review")
                .with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void newHotelTest() throws Exception {
        mvc.perform(get("/new-hotel")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("create/new-hotel"))
                .andExpect(view().name("create/new-hotel"));
    }

    @Test
    public void newHotelTestFalse() throws Exception {
        mvc.perform(get("/new-hotel")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void regHotelTest() throws Exception {
        mvc.perform(post("/reg-hotel").with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/hotels"));
    }

    @Test
    public void regHotelTestFalse() throws Exception {
        mvc.perform(post("/reg-hotel").with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void newTourTest() throws Exception {
        mvc.perform(get("/new-tour")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("hotels", "countries", "tourTypes"))
                .andExpect(forwardedUrl("create/new-tour"))
                .andExpect(view().name("create/new-tour"));
    }

    @Test
    public void newTourTestFalse() throws Exception {
        mvc.perform(get("/new-tour")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }

    @Test
    public void regTourTestTrue() throws Exception {
        mvc.perform(post("/reg-tour")
                .with(user("user").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/tours"));
    }

    @Test
    public void regTourTestTrueFalse() throws Exception {
        mvc.perform(post("/reg-tour")
                .with(user("user")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }
}
