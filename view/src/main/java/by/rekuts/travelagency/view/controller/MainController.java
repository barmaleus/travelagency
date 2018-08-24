package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.*;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class MainController {

    @Autowired
    private TourService tourService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private HotelService hotelService;

    @Transactional
    @GetMapping(value = {"/", "/tours"})
    public String viewTours(ModelMap model) {
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        return "tours";
    }

    @GetMapping(value = "/users")
    public String viewUsers(ModelMap model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/countries")
    public String viewCountries(ModelMap model) {
        List<Country> countries = countryService.getList(new CountrySpecification());
        model.addAttribute("countries", countries);
        return "countries";
    }

    @GetMapping(value = "/hotels")
    public String viewHotels(ModelMap model) {
        List<Hotel> hotels = hotelService.getList(new HotelSpecification());
        model.addAttribute("hotels", hotels);
        return "hotels";
    }

    @GetMapping(value = "/reviews")
    public String viewReviews(ModelMap model) {
        List<Review> reviews = reviewService.getList(new ReviewSpecification());
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @GetMapping(value = "/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/tours";
    }

    @GetMapping(value = "/new-user")
    public String getNewUser() {
        return "new-user";
    }

    @Transactional
    @GetMapping(value = "/tours/{tourId}")
    public String getTour(@PathVariable("tourId") int tourId, Model model) {
        Tour tour = tourService.getList(new TourSpecification(tourId)).get(0);
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(tourId);
        List<Review> reviews = reviewService.getList(specification);
        model.addAttribute("tour", tour);
        model.addAttribute("reviews", reviews);
        return "tour";
    }

    @Transactional
    @GetMapping(value = "/users/{userId}")
    public String getUser(@PathVariable("userId") int userId, Model model) {
        User user = userService.getList(new UserSpecification(userId)).get(0);
        TourSpecification specification = new TourSpecification();
        specification.setUserId(userId);
        List<Tour> tours = tourService.getList(specification);
        model.addAttribute("user", user);
        model.addAttribute("tours", tours);
        return "user";
    }

    @Transactional
    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        UserSpecification specification = new UserSpecification();
        specification.setLogin(login);
        User user = userService.getList(specification).get(0);
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(user.getId());
        List<Tour> tours = tourService.getList(tourSpecification);
        model.addAttribute("user", user);
        model.addAttribute("tours", tours);
        return "user";
    }

    @Transactional
    @GetMapping("/hotels/{hotelId}")
    public String getHotel(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("hotel", hotel);
        return "hotel";
    }
}
