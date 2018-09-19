package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.*;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
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

    @PostMapping("/update-country")
    public String updateCountry(@ModelAttribute Country country) {
        countryService.update(country);
        return "redirect:/countries";
    }

    @GetMapping(value = "/countries/{countryId}/update-country")
    public String updateCountry(@PathVariable("countryId") int countryId, Model model) {
        Country country = countryService.getList(new CountrySpecification(countryId)).get(0);
        model.addAttribute("country", country);
        return "update/update-country";
    }

    @PostMapping("/update-hotel")
    public String updateHotel(@ModelAttribute Hotel hotel) {
        hotelService.update(hotel);
        return "redirect:/hotels";
    }

    @GetMapping(value = "/hotels/{hotelId}/update-hotel")
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

    @PostMapping("/update-review")
    public String updateReview(
            @RequestParam(value = "id") int reviewId,
            @RequestParam(value = "user") int userId,
            @RequestParam(value = "tour") int tourId,
            @RequestParam(value = "text") String text
    ) {
        Review review = new Review();
        review.setId(reviewId);
        review.setUser(userService.getList(new UserSpecification(userId)).get(0));
        review.setTour(tourService.getList(new TourSpecification(tourId)).get(0));
        review.setText(text);
        reviewService.update(review);
        return "redirect:/reviews";
    }

    @GetMapping(value = "/reviews/{reviewId}/update-review")
    public String updateReview(@PathVariable("reviewId") int reviewId, Model model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        Review review = reviewService.getList(new ReviewSpecification(reviewId)).get(0);
        model.addAttribute("review", review);
        return "update/update-review";
    }

    @PostMapping("/update-tour")
    public String updateTour(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "photo") String photo,
            @RequestParam(value = "date") String date,
            @RequestParam(value = "duration") Integer duration,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "cost") Double cost,
            @RequestParam(value = "tourType") String tourType,
            @RequestParam(value = "hotel") Integer hotelId,
            @RequestParam(value = "country") Integer countryId
    ) {
        Tour tour = new Tour();
        tour.setId(id);
        tour.setPhoto(photo);
        LocalDate localDate = LocalDate.parse(date);
        tour.setDate(localDate);
        tour.setDuration(duration);
        tour.setDescription(description);
        tour.setCost(BigDecimal.valueOf(cost));
        tour.setTourType(Tour.TourType.valueOf(tourType));
        tour.setHotel(hotelService.getList(new HotelSpecification(hotelId)).get(0));
        tour.setCountry(countryService.getList(new CountrySpecification(countryId)).get(0));
        tourService.update(tour);
        return "redirect:/tours";
    }

    @GetMapping(value = "/tours/{tourId}/update-tour")
    public String updateTour(@PathVariable("tourId") int tourId, Model model) {
        List<Hotel> hotels = hotelService.getList(new HotelSpecification());
        List<Country> countries = countryService.getList(new CountrySpecification());
        List<Tour.TourType> tourTypes = Arrays.asList(Tour.TourType.values());
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

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/{userId}/update-user")
    public String updateUser(@PathVariable("userId") int userId, Model model) {
        List<User.UserRole> userRoles = Arrays.asList(User.UserRole.values());
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
