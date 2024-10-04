package by.rekuts.travelagency.view.controller.api;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.service.TourService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TourApiController {

    private final TourService tourService;

    //todo may add parameters for specification
    @GetMapping(value = {"/tours"})
    public List<Tour> getTours() {
        return tourService.getList(new TourSpecification());
    }

    @GetMapping(value = "/tours/{tourId}")
    public Tour getTour(@PathVariable("tourId") int tourId) {
        TourSpecification tourSpecification = new TourSpecification(tourId);
        List<Tour> tours = tourService.getList(tourSpecification);
        return tours.isEmpty() ? null : tours.get(0);
    }
}
