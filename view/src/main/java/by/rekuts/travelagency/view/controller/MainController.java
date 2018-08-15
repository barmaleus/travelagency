package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.logic.domain.Hotel;
import by.rekuts.travelagency.logic.domain.Review;
import by.rekuts.travelagency.logic.domain.Tour;
import by.rekuts.travelagency.logic.domain.User;
import by.rekuts.travelagency.logic.repository.HotelSpecification;
import by.rekuts.travelagency.logic.repository.ReviewSpecification;
import by.rekuts.travelagency.logic.repository.TourSpecification;
import by.rekuts.travelagency.logic.repository.UserSpecification;
import by.rekuts.travelagency.logic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("sessionUser")
@ComponentScan(resourcePattern = "/styles")
public class MainController {

    @Autowired
    private TourService tourService;
    @Autowired
    CountryService countryService;
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    HotelService hotelService;

    @Transactional
    @GetMapping
    public String viewTours(ModelMap model) {
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        return "home";
    }

    @GetMapping(value = "/users")
    public String viewUsers(ModelMap model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/sign-in")
    public String getSignIn() {
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute User user) {
        List<User> users = userService.getList(new UserSpecification());
        for (User someUser : users) {
            if (someUser.getLogin().equals(user.getLogin()) && someUser.getPassword().equals(user.getPassword())) {
                //user must be loggined

                return "redirect:/users";
            }
        }
        return "sign-in";
    }

    @Transactional
    @GetMapping(value = "/home/{tourId}")
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
    @GetMapping("/hotels/{hotelId}")
    public String getHotel(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("hotel", hotel);
        return "hotel";
    }
}
