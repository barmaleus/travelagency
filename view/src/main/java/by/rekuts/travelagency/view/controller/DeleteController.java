package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class DeleteController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/del-review")
    public String delReview(@RequestParam(value = "reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/tours";
    }
}
