package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class RegisterController {

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

    @PostMapping("/reg-user")
    public String signUp(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/tours";
    }

    @PostMapping("/reg-country")
    public String regCountry(@ModelAttribute Country country) {
        countryService.insert(country);
        return "redirect:/tours";
    }

    @GetMapping(value = "/new-review")
    public String newReview(ModelMap model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        return "new-review";
    }

    @Transactional
    @PostMapping(value = "/reg-review")
    public String regReview(
            @RequestParam(value = "user") Integer userId,
            @RequestParam(value = "tour") Integer tourId,
            @RequestParam(value = "text") String text
    ) {
        Review review = new Review();
        review.setDate(LocalDateTime.now());
        review.setUser(userService.getList(new UserSpecification(userId)).get(0));
        review.setTour(tourService.getList(new TourSpecification(tourId)).get(0));
        review.setText(text);
        reviewService.insert(review);
        return "redirect:/tours";
    }

    @GetMapping(value = "/new-hotel")
    public String newHotel(ModelMap model) {
        Hotel.Features[] features = Hotel.Features.values();
        List<String> stringFeatures = new ArrayList<>();
        for (Hotel.Features feature : features) {
            stringFeatures.add(feature.getValue());
        }
        model.addAttribute("features", stringFeatures);
        return "new-hotel";
    }

    @PostMapping("/reg-hotel")
    public String regHotel(@ModelAttribute Hotel hotel) {
        hotelService.insert(hotel);
        return "redirect:/tours";
    }

    @GetMapping(value = "/new-tour")
    public String newTour(ModelMap model) {
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
        return "new-tour";
    }

    @PostMapping(value = "/reg-tour")   //todo there are some bugs
    public String regTour(
            @RequestParam(value = "photo") String photo,
            @RequestParam(value = "date") LocalDate date,
            @RequestParam(value = "duration") Integer duration,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "cost") Double cost,
            @RequestParam(value = "tourType") String tourType,
            @RequestParam(value = "hotel") Integer hotelId,
            @RequestParam(value = "country") Integer countryId
            ) {
        Tour tour = new Tour();
        tour.setPhoto(photo);
        tour.setDate(date);
        tour.setDuration(duration);
        tour.setDescription(description);
        tour.setCost(BigDecimal.valueOf(cost));
        tour.setTourType(Tour.TourType.valueOf(tourType));
        tour.setHotel(hotelService.getList(new HotelSpecification(hotelId)).get(0));
        tour.setCountry(countryService.getList(new CountrySpecification(countryId)).get(0));
        tourService.insert(tour);
        return "redirect:/tours";
    }
}
