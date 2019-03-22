package by.rekuts.travelagency.view.controller;

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
        var tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        return "list/tours";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/users")
    public String viewUsers(ModelMap model) {
        var users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        return "list/users";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/countries")
    public String viewCountries(ModelMap model) {
        var countries = countryService.getList(new CountrySpecification());
        model.addAttribute("countries", countries);
        return "list/countries";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/hotels")
    public String viewHotels(ModelMap model) {
        var hotels = hotelService.getList(new HotelSpecification());
        model.addAttribute("hotels", hotels);
        return "list/hotels";
    }

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/reviews")
    public String viewReviews(ModelMap model) {
        var reviews = reviewService.getList(new ReviewSpecification());
        model.addAttribute("reviews", reviews);
        return "list/reviews";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/tours/{tourId}")
    public String getTour(@PathVariable("tourId") int tourId, Model model) {
        var sessionUserId = new ControllerHelper().getSessionUserId();
        var tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);
        var sesUserTours = tourService.getList(tourSpecification);
        var isTourFavorite = false;
        for (var tour : sesUserTours) {
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
        var user = userService.getList(new UserSpecification(userId)).get(0);
        var specification = new TourSpecification();
        specification.setUserId(userId);
        var tours = tourService.getList(specification);
        var reviewSpecification = new ReviewSpecification();
        reviewSpecification.setUserId(userId);
        var reviews = reviewService.getList(reviewSpecification);
        model.addAttribute("user", user);
        model.addAttribute("tours", tours);
        model.addAttribute("reviews", reviews);
        return "user";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @PostMapping(value = "/tours/{tourId}")
    public String getFavoriteTour(@PathVariable("tourId") int tourId, boolean addToFavorites, Model model) {
        var sessionUserId = new ControllerHelper().getSessionUserId();
        var tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);

        var sessionUser = userService.getList(new UserSpecification(sessionUserId)).get(0);

        if (addToFavorites) {
            var addedTour = tourService.getList(new TourSpecification(tourId)).get(0);
            userService.addTourToFavorites(sessionUser, addedTour);
        } else {
            var addedTour = tourService.getList(new TourSpecification(tourId)).get(0);
            userService.removeTourFromFavorites(sessionUser, addedTour);
        }

        addTourAndReviewsToModel(tourId, model);
        return "redirect:/tours/{tourId}";
    }

    private void addTourAndReviewsToModel(int tourId, Model model) {
        var tour = tourService.getList(new TourSpecification(tourId)).get(0);
        model.addAttribute("tour", tour);
        var specification = new ReviewSpecification();
        specification.setTourId(tourId);
        var reviews = reviewService.getList(specification);
        model.addAttribute("reviews", reviews);
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        var sessionUserId = new ControllerHelper().getSessionUserId();
        var tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);
        var tours = tourService.getList(tourSpecification);
        var sessionUser = userService.getList(new UserSpecification(sessionUserId)).get(0);
        model.addAttribute("user", sessionUser);
        model.addAttribute("tours", tours);
        return "user";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping("/hotels/{hotelId}")
    public String getHotel(@PathVariable("hotelId") int hotelId, Model model) {
        var hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("hotel", hotel);
        return "hotel";
    }
}
