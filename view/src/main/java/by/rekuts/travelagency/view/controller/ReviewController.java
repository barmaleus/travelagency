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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
//@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class ReviewController {

    private final TourService tourService;
    private final UserService userService;
    private final ReviewService reviewService;

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/reviews")
    public String viewReviews(ModelMap model) {
        List<Review> reviews = reviewService.getList(new ReviewSpecification());
        model.addAttribute("reviews", reviews);
        return "list/reviews";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/new-review")
    public String newReview(ModelMap model) {
        List<Tour> tours = addUsersToursSesUserToModel(model);
        model.addAttribute("tourId", tours.get(tours.size() - 1).getId()); //todo check it
        return "create/new-review";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/tours/{tourId}/add-review")
    public String newUserReview(@PathVariable int tourId, ModelMap model) {
        addUsersToursSesUserToModel(model);
        model.addAttribute("tourId", tourId);
        return "create/new-review";
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @PostMapping(value = "/reg-review")
    public String regReview(@ModelAttribute Review review) {
        review.setDate(LocalDateTime.now());
        reviewService.insert(review);
        return "redirect:/tours";
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/del-review")
    public String delReview(@RequestParam(value = "reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/tours";
    }

    private List<Tour> addUsersToursSesUserToModel(ModelMap model) {
        List<User> users = userService.getList(new UserSpecification());
        model.addAttribute("users", users);
        List<Tour> tours = tourService.getList(new TourSpecification());
        model.addAttribute("tours", tours);
        int sesUserId = new ControllerHelper().getSessionUserId();
        model.addAttribute("sesUserId", sesUserId);
        return tours;
    }

}
