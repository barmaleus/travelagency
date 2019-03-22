package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class CreateController {

    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private TourService tourService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private HotelService hotelService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/reg-user")
    public String signUp(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/tours";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reg-country")
    public String regCountry(@ModelAttribute Country country) {
        countryService.insert(country);
        return "redirect:/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/new-review")
    public String newReview(ModelMap model) {
        var tours = addUsersToursSesUserToModel(model);
        model.addAttribute("tourId", tours.get(tours.size()-1).getId()); //todo check it
        return "create/new-review";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/tours/{tourId}/add-review")
    public String newUserReview(@PathVariable int tourId, ModelMap model) {
        addUsersToursSesUserToModel(model);
        model.addAttribute("tourId", tourId);
        return "create/new-review";
    }

    private List<Tour> addUsersToursSesUserToModel(ModelMap model) {
        var users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        var tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        var sesUserId = new ControllerHelper().getSessionUserId();
        model.addAttribute("sesUserId", sesUserId);
        return tours;
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @PostMapping(value = "/reg-review")
    public String regReview(@ModelAttribute Review review) {
        review.setDate(LocalDateTime.now());
        reviewService.insert(review);
        return "redirect:/tours";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/new-hotel")
    public String newHotel(ModelMap model) {
        var features = Hotel.Features.values();
        var stringFeatures = new ArrayList<>();
        for (var feature : features) {
            stringFeatures.add(feature.getValue());
        }
        model.addAttribute("features", stringFeatures);
        return "create/new-hotel";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reg-hotel")
    public String regHotel(@ModelAttribute Hotel hotel) {
        hotelService.insert(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/new-tour")
    public String newTour(ModelMap model) {
        var hotels = hotelService.getList(new HotelSpecification());
        var countries = countryService.getList(new CountrySpecification());
        var tourTypes = Arrays.asList(Tour.TourType.values());
        var stringTourTypes = new ArrayList<>();
        for (var tourType : tourTypes) {
            stringTourTypes.add(tourType.name());
        }
        model.addAttribute("hotels", hotels);
        model.addAttribute("countries", countries);
        model.addAttribute("tourTypes", stringTourTypes);
        return "create/new-tour";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/reg-tour")
    public String regTour(@ModelAttribute Tour tour) {
        tourService.insert(tour);
        return "redirect:/tours";
    }
}
