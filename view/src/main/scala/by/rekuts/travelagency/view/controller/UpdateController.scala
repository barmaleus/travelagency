package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain._
import by.rekuts.travelagency.repository._
import by.rekuts.travelagency.service._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PathVariable, PostMapping}

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = Array("by.rekuts.**"))
@PreAuthorize("hasRole('ROLE_ADMIN')")
class UpdateController @Autowired() (countryService: CountryService, hotelService: HotelService, reviewService: ReviewService, tourService: TourService, userService: UserService) {
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/update-country"))
  def updateCountry(@ModelAttribute country: Country): String = {
    countryService.update(country)
    "redirect:/countries"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/countries/{countryId}/update"))
  def updateCountry(@PathVariable("countryId") countryId: Int, model: Model): String = {
    val country: Country = countryService.getList(new CountrySpecification(countryId)).get(0)
    model.addAttribute("country", country)
    "update/update-country"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/update-hotel"))
  def updateHotel(@ModelAttribute hotel: Hotel): String = {
    hotelService.update(hotel)
    "redirect:/hotels"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/hotels/{hotelId}/update"))
  def updateHotel(@PathVariable("hotelId") hotelId: Int, model: Model): String = {
    val features: Enumeration#ValueSet = Features.values
    val stringFeatures: util.ArrayList[AnyRef] = new util.ArrayList[AnyRef]
    features.foreach((feature: Enumeration#Value) => stringFeatures.add(feature.toString))
    val hotel: Hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0)
    model.addAttribute("features", stringFeatures)
    model.addAttribute("hotel", hotel)
    "update/update-hotel"
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(Array("/update-review"))
  def updateReview(@ModelAttribute review: Review): String = {
    reviewService.update(review)
    "redirect:/reviews"
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(value = Array("/reviews/{reviewId}/update"))
  def updateReview(@PathVariable("reviewId") reviewId: Int, model: Model): String = {
    val users: util.List[User] = userService.getList(new UserSpecification)
    model.addAttribute("users", users)
    val tours: util.List[Tour] = tourService.getList(new TourSpecification)
    model.addAttribute("tours", tours)
    val review: Review = reviewService.getList(new ReviewSpecification(reviewId)).get(0)
    model.addAttribute("review", review)
    "update/update-review"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/update-tour")) def updateTour(@ModelAttribute tour: Tour): String = {
    tourService.update(tour)
    "redirect:/tours"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/tours/{tourId}/update"))
  def updateTour(@PathVariable("tourId") tourId: Int, model: Model): String = {
    val hotels: util.List[Hotel] = hotelService.getList(new HotelSpecification)
    val countries: util.List[Country] = countryService.getList(new CountrySpecification)
    val tourTypes: Enumeration#ValueSet = TourType.values
    val stringTourTypes: util.ArrayList[AnyRef] = new util.ArrayList[AnyRef]
    tourTypes.foreach((tType: Enumeration#Value) => stringTourTypes.add(tType.toString))
    model.addAttribute("hotels", hotels)
    model.addAttribute("countries", countries)
    model.addAttribute("tourTypes", stringTourTypes)
    val tour: Tour = tourService.getList(new TourSpecification(tourId)).get(0)
    model.addAttribute("tour", tour)
    "update/update-tour"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/update-user"))
  def updateUser(@ModelAttribute user: User): String = {
    userService.update(user)
    "redirect:/users"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/users/{userId}/update"))
  def updateUser(@PathVariable("userId") userId: Int, model: Model): String = {
    val userRoles: Enumeration#ValueSet = UserRole.values
    val stringUserRoles: util.ArrayList[AnyRef] = new util.ArrayList[AnyRef]
    userRoles.foreach((role: Enumeration#Value) => stringUserRoles.add(role.toString))
    model.addAttribute("userRoles", stringUserRoles)
    val tours: util.List[Tour] = tourService.getList(new TourSpecification)
    model.addAttribute("tours", tours)
    val user: User = userService.getList(new UserSpecification(userId)).get(0)
    model.addAttribute("user", user)
    "update/update-user"
  }
}
