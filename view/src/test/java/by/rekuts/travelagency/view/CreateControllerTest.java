package by.rekuts.travelagency.view;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.TourService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    public void newReviewTest() throws Exception {
        Tour tour = new Tour();
        tour.setId(6);
        List<Tour> tours = Collections.singletonList(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).with(user("user").roles("ADMIN")))
                .andExpect(view().name("create/new-review"));
    }

    @Test
    public void newReviewTestFalse1() throws Exception {
        Tour tour = new Tour();
        tour.setId(6);
        List<Tour> tours = Collections.singletonList(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void newReviewTestFalse2() throws Exception {
        Tour tour = new Tour();
        tour.setId(6);
        List<Tour> tours = Collections.singletonList(tour);
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).with(user("user").roles("MEMBER")))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/error"));
    }
}
