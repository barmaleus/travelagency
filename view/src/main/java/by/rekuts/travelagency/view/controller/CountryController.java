package by.rekuts.travelagency.view.controller;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
//@ComponentScan(resourcePattern = "/styles", basePackages = "by.rekuts.**")
public class CountryController {

    private final CountryService countryService;

    @PreAuthorize("authentication.authenticated")
    @GetMapping(value = "/countries")
    public String viewCountries(ModelMap model) {
        List<Country> countries = countryService.getList(new CountrySpecification());
        model.addAttribute("countries", countries);
        return "list/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/reg-country")
    public String regCountry(@ModelAttribute Country country) {
        countryService.insert(country);
        return "redirect:/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-country")
    public String updateCountry(@ModelAttribute Country country) {
        countryService.update(country);
        return "redirect:/countries";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/countries/{countryId}/update")
    public String updateCountry(@PathVariable("countryId") int countryId, Model model) {
        Country country = countryService.getList(new CountrySpecification(countryId)).get(0);
        model.addAttribute("country", country);
        return "update/update-country";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/del-country")
    public String delCountry(@RequestParam(value = "countryId") int countryId) {
        countryService.delete(countryId);
        return "redirect:/countries";
    }
}
