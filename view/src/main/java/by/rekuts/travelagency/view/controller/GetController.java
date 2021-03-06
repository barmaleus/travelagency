package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.*;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class GetController {

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
        return "list/tours";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/users")
    public String viewUsers(ModelMap model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        return "list/users";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/countries")
    public String viewCountries(ModelMap model) {
        List<Country> countries = countryService.getList(new CountrySpecification());
        model.addAttribute("countries", countries);
        return "list/countries";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/hotels")
    public String viewHotels(ModelMap model) {
        List<Hotel> hotels = hotelService.getList(new HotelSpecification());
        model.addAttribute("hotels", hotels);
        return "list/hotels";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/reviews")
    public String viewReviews(ModelMap model) {
        List<Review> reviews = reviewService.getList(new ReviewSpecification());
        model.addAttribute("reviews", reviews);
        return "list/reviews";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/tours/{tourId}")
    public String getTour(@PathVariable("tourId") int tourId, Model model) {
        int sessionUserId = new ControllerHelper().getSessionUserId();
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);
        List<Tour> sesUserTours = tourService.getList(tourSpecification);
        boolean isTourFavorite = false;
        for (Tour tour : sesUserTours) {
            if (tour.getId() == tourId) {
                isTourFavorite = true;
                break;
            }
        }
        model.addAttribute("isFavorite", isTourFavorite);

        addTourAndReviewsToModel(tourId, model);
        return "tour";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/users/{userId}")
    public String getUser(@PathVariable("userId") int userId, Model model) {
        User user = userService.getList(new UserSpecification(userId)).get(0);
        TourSpecification specification = new TourSpecification();
        specification.setUserId(userId);
        List<Tour> tours = tourService.getList(specification);
        ReviewSpecification reviewSpecification = new ReviewSpecification();
        reviewSpecification.setUserId(userId);
        List<Review> reviews = reviewService.getList(reviewSpecification);
        model.addAttribute("user", user);
        model.addAttribute("tours", tours);
        model.addAttribute("reviews", reviews);
        return "user";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @PostMapping(value = "/tours/{tourId}")
    public String getFavoriteTour(@PathVariable("tourId") int tourId, boolean addToFavorites, Model model) {
        int sessionUserId = new ControllerHelper().getSessionUserId();
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);

        User sessionUser = userService.getList(new UserSpecification(sessionUserId)).get(0);

        if (addToFavorites) {
            Tour addedTour = tourService.getList(new TourSpecification(tourId)).get(0);
            userService.addTourToFavorites(sessionUser, addedTour);
        } else {
            Tour addedTour = tourService.getList(new TourSpecification(tourId)).get(0);
            userService.removeTourFromFavorites(sessionUser, addedTour);
        }

        addTourAndReviewsToModel(tourId, model);
        return "redirect:/tours/{tourId}";
    }

    private void addTourAndReviewsToModel(int tourId, Model model) {
        Tour tour = tourService.getList(new TourSpecification(tourId)).get(0);
        model.addAttribute("tour", tour);
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(tourId);
        List<Review> reviews = reviewService.getList(specification);
        model.addAttribute("reviews", reviews);
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        int sessionUserId = new ControllerHelper().getSessionUserId();
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);
        List<Tour> tours = tourService.getList(tourSpecification);
        User sessionUser = userService.getList(new UserSpecification(sessionUserId)).get(0);
        model.addAttribute("user", sessionUser);
        model.addAttribute("tours", tours);
        return "user";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping("/hotels/{hotelId}")
    public String getHotel(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("hotel", hotel);
        return "hotel";
    }
}
