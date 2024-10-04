package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.*;
import by.rekuts.travelagency.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
//@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class TourController {

    private final TourService tourService;
    private final CountryService countryService;
    private final UserService userService;
    private final ReviewService reviewService;
    private final HotelService hotelService;

    @Transactional
    @GetMapping(value = {"/", "/tours"})
    public String viewTours(ModelMap model) {
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        return "list/tours";
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/new-tour")
    public String newTour(ModelMap model) {
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
        return "create/new-tour";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/reg-tour")
    public String regTour(@ModelAttribute Tour tour) {
        tourService.insert(tour);
        return "redirect:/tours";
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
    @PostMapping("/del-tour")
    public String delTour(@RequestParam(value = "tourId") int tourId) {
        tourService.delete(tourId);
        return "redirect:/tours";
    }

    private void addTourAndReviewsToModel(int tourId, Model model) {
        Tour tour = tourService.getList(new TourSpecification(tourId)).get(0);
        model.addAttribute("tour", tour);
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(tourId);
        List<Review> reviews = reviewService.getList(specification);
        model.addAttribute("reviews", reviews);
    }
}
