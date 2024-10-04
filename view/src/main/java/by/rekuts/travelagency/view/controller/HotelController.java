package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.HotelFeature;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.service.HotelService;
import lombok.RequiredArgsConstructor;
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
public class HotelController {

    private final HotelService hotelService;

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/hotels")
    public String viewHotels(ModelMap model) {
        List<Hotel> hotels = hotelService.getList(new HotelSpecification());
        model.addAttribute("hotels", hotels);
        return "list/hotels";
    }

    @PreAuthorize("authentication.authenticated")
    @Transactional
    @GetMapping("/hotels/{hotelId}")
    public String getHotel(@PathVariable("hotelId") int hotelId, Model model) {
        Hotel hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("hotel", hotel);
        return "hotel";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/new-hotel")
    public String newHotel(ModelMap model) {
        HotelFeature[] features = HotelFeature.values();
        List<String> stringFeatures = new ArrayList<>();
        for (HotelFeature feature : features) {
            stringFeatures.add(feature.getValue());
        }
        model.addAttribute("features", stringFeatures);
        return "create/new-hotel";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reg-hotel")
    public String regHotel(@ModelAttribute Hotel hotel) {
        hotelService.insert(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-hotel")
    public String updateHotel(@ModelAttribute Hotel hotel) {
        hotelService.update(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/hotels/{hotelId}/update")
    public String updateHotel(@PathVariable("hotelId") int hotelId, Model model) {
        HotelFeature[] features = HotelFeature.values();
        List<String> stringFeatures = new ArrayList<>();
        for (HotelFeature feature : features) {
            stringFeatures.add(feature.getValue());
        }
        Hotel hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0);
        model.addAttribute("features", stringFeatures);
        model.addAttribute("hotel", hotel);
        return "update/update-hotel";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/del-hotel")
    public String delHotel(@RequestParam(value = "hotelId") int hotelId) {
        hotelService.delete(hotelId);
        return "redirect:/hotels";
    }
}
