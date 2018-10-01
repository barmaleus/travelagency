package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.HotelService;
import by.rekuts.travelagency.service.TourService;
import by.rekuts.travelagency.service.UserService;
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
import java.util.LinkedList;
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
public class GetControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;
    @Autowired
    private HotelService hotelService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    private static List<User> users = new LinkedList<>();
    private static User user = new User();
    static {
        user.setLogin("login");
        user.setRole(User.UserRole.ROLE_ADMIN);
        users.add(user);
    }

    @Test
    public void viewToursTest1() throws Exception {
        mvc.perform(get("/"))
                .andExpect(view().name("list/tours"));
    }

    @Test (expected = AssertionError.class)
    public void viewToursTestFalse() throws Exception {
        mvc.perform(get("/"))
                .andExpect(view().name("list/countries"));
    }

    @Test
    public void viewToursTest2() throws Exception {
        mvc.perform(get("/tours")).andExpect(view().name("list/tours"));
    }

    @Test
    public void viewUsersTest() throws Exception {
        mvc.perform(get("/users").with(user("user")))
                .andExpect(view().name("list/users"));
    }

    @Test
    public void viewUsersTestFalse() throws Exception {
        mvc.perform(get("/users").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void viewCountriesTest() throws Exception {
        mvc.perform(get("/countries").with(user("user")))
                .andExpect(view().name("list/countries"));
    }

    @Test
    public void viewCountriesTestFalse() throws Exception {
        mvc.perform(get("/countries").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void viewHotelsTest() throws Exception {
        mvc.perform(get("/hotels").with(user("user")))
                .andExpect(view().name("list/hotels"));
    }

    @Test
    public void viewHotelsTestFalse() throws Exception {
        mvc.perform(get("/hotels").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void viewReviewsTest() throws Exception {
        mvc.perform(get("/reviews").with(user("user")))
                .andExpect(view().name("list/reviews"));
    }

    @Test
    public void viewReviewsTestFalse() throws Exception {
        mvc.perform(get("/reviews").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void getTourTest() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        List<Tour> tours = Collections.singletonList(new Tour());
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).with(user("user")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("tour"))
                .andExpect(model().attribute("tour", tours.get(0)));
    }

    @Test
    public void getTourTestFalse1() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        List<Tour> tours = Collections.singletonList(new Tour());
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void getTourTestFalse2() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        List<Tour> tours = Collections.singletonList(new Tour());
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(get("/tours/{tourId}", "tour").sessionAttr("sesUserId", 4).with(user("user")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getUserTest() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        mvc.perform(get("/users/{userId}", "178").with(user("user")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("user"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    public void getUserTestFalse() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        mvc.perform(get("/users/{userId}", "178").with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void getFavoriteTourTest() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        List<Tour> tours = Collections.singletonList(new Tour());
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(post("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).with(user("user")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tours/178"))
                .andExpect(model().attribute("tour", tours.get(0)));
    }

    @Test
    public void getFavoriteTourTestFalse() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        List<Tour> tours = Collections.singletonList(new Tour());
        when(tourService.getList(any(TourSpecification.class))).thenReturn(tours);
        mvc.perform(post("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void getProfileTest() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        mvc.perform(get("/profile").sessionAttr("sesUserId", 4).with(user("user")))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("user"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    public void getProfileTestFalse() throws Exception {
        when(userService.getList(any(UserSpecification.class))).thenReturn(users);
        mvc.perform(get("/profile").sessionAttr("sesUserId", 4).with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }

    @Test
    public void getHotelTest() throws Exception {
        List<Hotel> hotels = Collections.singletonList(new Hotel());
        when(hotelService.getList(any(HotelSpecification.class))).thenReturn(hotels);
        mvc.perform(get("/hotels/{hotelId}", 8).with(user("user")))
                .andExpect(view().name("hotel"))
                .andExpect(model().attribute("hotel", hotels.get(0)));
    }

    @Test
    public void getHotelTestFalse() throws Exception {
        List<Hotel> hotels = Collections.singletonList(new Hotel());
        when(hotelService.getList(any(HotelSpecification.class))).thenReturn(hotels);
        mvc.perform(get("/hotels/{hotelId}", 8).with(anonymous()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/sign-in"));
    }
}
