package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.*;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UpdateController {
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-country")
    public String updateCountry(@ModelAttribute Country country) {
        countryService.update(country);
        return "redirect:/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/countries/{countryId}/update")
    public String updateCountry(@PathVariable("countryId") int countryId, Model model) {
        Country country = countryService.getList(new CountrySpecification(countryId)).get(0);
        model.addAttribute("country", country);
        return "update/update-country";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-hotel")
    public String updateHotel(@ModelAttribute Hotel hotel) {
        hotelService.update(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/hotels/{hotelId}/update")
    public String updateHotel(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel.Features[] features = Hotel.Features.values();
        List<String> stringFeatures = new ArrayList<>();
        for (Hotel.Features feature : features) {
            stringFeatures.add(feature.getValue());
        }
        Hotel hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("features", stringFeatures);
        model.addAttribute("hotel", hotel);
        return "update/update-hotel";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update-review")
    public String updateReview(@ModelAttribute Review review) {
        reviewService.update(review);
        return "redirect:/reviews";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/reviews/{reviewId}/update")
    public String updateReview(@PathVariable("reviewId") int reviewId, Model model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        Review review = reviewService.getList(new ReviewSpecification(reviewId)).get(0);
        model.addAttribute("review", review);
        return "update/update-review";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-tour")
    public String updateTour(@ModelAttribute Tour tour) {
        tourService.update(tour);
        return "redirect:/tours";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/tours/{tourId}/update")
    public String updateTour(@PathVariable("tourId") int tourId, Model model) {
        List<Hotel> hotels = hotelService.getList(new HotelSpecification());
        List<Country> countries = countryService.getList(new CountrySpecification());
        Tour.TourType[] tourTypes = Tour.TourType.values();
        List<String> stringTourTypes = new ArrayList<>();
        for (Tour.TourType tourType : tourTypes) {
            stringTourTypes.add(tourType.name());
        }
        model.addAttribute("hotels", hotels);
        model.addAttribute("countries", countries);
        model.addAttribute("tourTypes", stringTourTypes);
        Tour tour = tourService.getList(new TourSpecification(tourId)).get(0);
        model.addAttribute("tour", tour);
        return "update/update-tour";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/users/{userId}/update")
    public String updateUser(@PathVariable("userId") int userId, Model model) {
        User.UserRole[] userRoles = User.UserRole.values();
        List<String> stringUserRoles = new ArrayList<>();
        for (User.UserRole userRole : userRoles) {
            stringUserRoles.add(userRole.name());
        }
        model.addAttribute("userRoles", stringUserRoles);
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        User user = userService.getList(new UserSpecification(userId)).get(0);
        model.addAttribute("user", user);
        return "update/update-user";
    }
}
