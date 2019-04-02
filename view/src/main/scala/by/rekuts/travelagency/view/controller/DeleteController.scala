package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain.Tour
import by.rekuts.travelagency.repository.TourSpecification
import by.rekuts.travelagency.service._
import by.rekuts.travelagency.view.exception.DeleteException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{PostMapping, RequestParam}

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = Array("by.rekuts.**"))
class DeleteController @Autowired() (reviewService: ReviewService, countryService: CountryService, tourService: TourService, hotelService: HotelService, userService: UserService) {
  @PreAuthorize("isAuthenticated()")
  @PostMapping(Array("/del-review"))
  def delReview(@RequestParam(value = "reviewId") reviewId: Int): String = {
    reviewService.delete(reviewId)
    "redirect:/tours"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/del-country"))
  def delCountry(@RequestParam(value = "countryId") countryId: Int): String = {
    val tours: util.List[Tour] = tourService.getList(new TourSpecification)
    import scala.collection.JavaConversions._
    for (tour <- tours) {
      if (tour.country.id == countryId) throw new DeleteException("Country " + tour.country.name + " with id " + countryId + " cannot be " + "deleted because of tour with id " + tour.id + " referenced on it")
    }
    countryService.delete(countryId)
    "redirect:/countries"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/del-hotel"))
  def delHotel(@RequestParam(value = "hotelId") hotelId: Int): String = {
    val tours: util.List[Tour] = tourService.getList(new TourSpecification)
    import scala.collection.JavaConversions._
    for (tour <- tours) {
      if (tour.hotel.id == hotelId) throw new DeleteException("Hotel " + tour.hotel.name + " with id " + hotelId + " cannot be " + "deleted because of tour with id " + tour.id + " referenced on it")
    }
    hotelService.delete(hotelId)
    "redirect:/hotels"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/del-tour"))
  def delTour(@RequestParam(value = "tourId") tourId: Int): String = {
    tourService.delete(tourId)
    "redirect:/tours"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/del-user"))
  def delUser(@RequestParam(value = "userId") userId: Int): String = {
    userService.delete(userId)
    "redirect:/users"
  }
}
