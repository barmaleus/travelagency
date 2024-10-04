package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.Review;
import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.ReviewSpecification;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.ReviewService;
import by.rekuts.travelagency.service.TourService;
import by.rekuts.travelagency.service.UserService;
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
public class UserController {

    private final UserService userService;
    private final TourService tourService;
    private final ReviewService reviewService;

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/users")
    public String viewUsers(ModelMap model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        return "list/users";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/users/{userId}")
    public String getUser(@PathVariable("userId") int userId, Model model) {
        User user = userService.getList(new UserSpecification(userId)).get(0);
        TourSpecification specification = new TourSpecification();
        specification.setUserId(userId);
        List<Tour> tours = tourService.getList(specification);
        ReviewSpecification reviewSpecification = new ReviewSpecification();
        reviewSpecification.setUserId(userId);
        List<Review> reviews = reviewService.getList(reviewSpecification);
        model.addAttribute("user", user);
        model.addAttribute("tours", tours);
        model.addAttribute("reviews", reviews);
        return "user";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        int sessionUserId = new ControllerHelper().getSessionUserId();
        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setUserId(sessionUserId);
        List<Tour> tours = tourService.getList(tourSpecification);
        User sessionUser = userService.getList(new UserSpecification(sessionUserId)).get(0);
        model.addAttribute("user", sessionUser);
        model.addAttribute("tours", tours);
        return "user";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/reg-user")
    public String signUp(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/tours";
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/del-user")
    public String delUser(@RequestParam(value = "userId") int userId) {
        userService.delete(userId);
        return "redirect:/users";
    }
}
