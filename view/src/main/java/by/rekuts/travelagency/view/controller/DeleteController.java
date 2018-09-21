package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.*;
import by.rekuts.travelagency.view.exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class DeleteController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private TourService tourService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    @PostMapping("/del-review")
    public String delReview(@RequestParam(value = "reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/reviews";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/del-country")
    public String delCountry(@RequestParam(value = "countryId") int countryId) {
        List<Tour> tours = tourService.getList(new TourSpecification());
        for (Tour tour : tours) {
            if (tour.getCountry().getId() == countryId) {
                throw new DeleteException(
                        "Country " + tour.getCountry().getName() + " with id " + countryId + " cannot be " +
                                "deleted because of tour with id " + tour.getId() + " referenced on it"
                );
            }
        }
        countryService.delete(countryId);
        return "redirect:/countries";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/del-hotel")
    public String delHotel(@RequestParam(value = "hotelId") int hotelId) {
        List<Tour> tours = tourService.getList(new TourSpecification());
        for (Tour tour : tours) {
            if (tour.getHotel().getId() == hotelId) {
                throw new DeleteException(
                        "Hotel " + tour.getHotel().getName() + " with id " + hotelId + " cannot be " +
                                "deleted because of tour with id " + tour.getId() + " referenced on it"
                );
            }
        }
        hotelService.delete(hotelId);
        return "redirect:/hotels";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/del-tour")
    public String delTour(@RequestParam(value = "tourId") int tourId) {
        tourService.delete(tourId);
        return "redirect:/tours";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/del-user")
    public String delUser(@RequestParam(value = "userId") int userId) {
        userService.delete(userId);
        return "redirect:/users";
    }
}
