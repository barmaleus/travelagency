package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.*;
import by.rekuts.travelagency.repository.*;
import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
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

    @Transactional
    @GetMapping(value = "/tours/{tourId}")
    public String getTour(@PathVariable("tourId") int tourId, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User sesUser = userService.getList(new UserSpecification((int)session.getAttribute("sesUserId"))).get(0);
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sesUser.getId());
        List<Tour> sesUserTours = tourService.getList(tourSpecification);
        boolean isTourFavorite = false;
        for (Tour tour : sesUserTours) {
            if (tour.getId() == tourId) {
                isTourFavorite = true;
                break;
            }
        }
        model.addAttribute("isFavorite", isTourFavorite);

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
    @PostMapping(value = "/tours/{tourId}")
    public String getFavoriteTour(@PathVariable("tourId") int tourId, boolean addToFavorites, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User sesUser = userService.getList(new UserSpecification((int)session.getAttribute("sesUserId"))).get(0);
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sesUser.getId());

        if (addToFavorites) {
            Tour addedTour = tourService.getList(new TourSpecification(tourId)).get(0);
            userService.addTourToFavorites(sesUser, addedTour);
        } else {
            Tour addedTour = tourService.getList(new TourSpecification(tourId)).get(0);
            userService.removeTourFromFavorites(sesUser, addedTour);
        }
        Tour tour = tourService.getList(new TourSpecification(tourId)).get(0);
        ReviewSpecification specification = new ReviewSpecification();
        specification.setTourId(tourId);
        List<Review> reviews = reviewService.getList(specification);
        model.addAttribute("tour", tour);
        model.addAttribute("reviews", reviews);
        return "redirect:/tours/{tourId}";
    }

    @Transactional
    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User user = (User) session.getAttribute("sesUser");
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
