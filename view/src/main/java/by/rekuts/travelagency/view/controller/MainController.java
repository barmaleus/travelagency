package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.logic.domain.Country;
import by.rekuts.travelagency.logic.domain.Tour;
import by.rekuts.travelagency.logic.domain.User;
import by.rekuts.travelagency.logic.repository.CountrySpecification;
import by.rekuts.travelagency.logic.repository.TourSpecification;
import by.rekuts.travelagency.logic.repository.UserSpecification;
import by.rekuts.travelagency.logic.service.CountryService;
import by.rekuts.travelagency.logic.service.TourService;
import by.rekuts.travelagency.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("ToursList")
public class MainController {

    @Autowired
    private TourService tourService;
    @Autowired
    CountryService countryService;
    @Autowired
    UserService userService;

//    @ModelAttribute("ToursList")
//    public List<Tour> tours() {
//        return tourService.getList(new TourSpecification());
//    }

    @Transactional
    @GetMapping
    public String viewTours(ModelMap model) {
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        return "home";
    }

//    @GetMapping(value = "/")
//    public String viewCountries(ModelMap model) {
//
//        List<Country> countries = countryService.getList(new CountrySpecification());
//        model.addAttribute("countries", countries);
//        return "countries";
//    }

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

    @Transactional
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/sign-in")
    public String getSignIn() {
        return "sign-in";
    }

    @Transactional
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
}
