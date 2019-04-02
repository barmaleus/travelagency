package by.rekuts.travelagency.view.controller

import java.time.LocalDateTime
import java.util

import by.rekuts.travelagency.domain._
import by.rekuts.travelagency.repository.{CountrySpecification, HotelSpecification, TourSpecification, UserSpecification}
import by.rekuts.travelagency.service._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PathVariable, PostMapping}

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = Array("by.rekuts.**"))
class CreateController @Autowired() (userService: UserService, countryService: CountryService, tourService: TourService, reviewService: ReviewService, hotelService: HotelService) {
  @PreAuthorize("isAnonymous()")
  @PostMapping(Array("/reg-user"))
  def signUp(@ModelAttribute user: User): String = {
    userService.insert(user)
    "redirect:/tours"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/reg-country"))
  def regCountry(@ModelAttribute country: Country): String = {
    countryService.insert(country)
    "redirect:/countries"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/new-review"))
  def newReview(model: ModelMap): String = {
    val tours: util.List[Tour] = addUsersToursSesUserToModel(model)
    model.addAttribute("tourId", tours.get(tours.size - 1).id) //todo check it
    "create/new-review"
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping(value = Array("/tours/{tourId}/add-review"))
  def newUserReview(@PathVariable tourId: String, model: ModelMap): String = {

    addUsersToursSesUserToModel(model)
    model.addAttribute("tourId", tourId.toInt)
    "create/new-review"
  }

  private def addUsersToursSesUserToModel(model: ModelMap): util.List[Tour] = {
    val users: util.List[User] = userService.getList(new UserSpecification)
    model.addAttribute("users", users)
    val tours: util.List[Tour] = tourService.getList(new TourSpecification)
    model.addAttribute("tours", tours)
    val sesUserId: Int = new ControllerHelper().getSessionUserId
    model.addAttribute("sesUserId", sesUserId)
    tours
  }

  @PreAuthorize("isAuthenticated()")
  @Transactional
  @PostMapping(value = Array("/reg-review"))
  def regReview(@ModelAttribute review: Review): String = {
    review.date_$eq(LocalDateTime.now)
    reviewService.insert(review)
    "redirect:/tours"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/new-hotel"))
  def newHotel(model: ModelMap): String = {
    val features: Enumeration#ValueSet = Features.values
    val stringFeatures: util.ArrayList[AnyRef] = new util.ArrayList[AnyRef]
    features.foreach((e: Enumeration#Value) => stringFeatures.add(e.toString))
    model.addAttribute("features", stringFeatures)
    "create/new-hotel"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(Array("/reg-hotel")) def regHotel(@ModelAttribute hotel: Hotel): String = {
    hotelService.insert(hotel)
    "redirect:/hotels"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping(value = Array("/new-tour"))
  def newTour(model: ModelMap): String = {
    val hotels: util.List[Hotel] = hotelService.getList(new HotelSpecification)
    val countries: util.List[Country] = countryService.getList(new CountrySpecification)
    val tourTypes: util.List[Enumeration#ValueSet] = util.Arrays.asList(TourType.values)
    val stringTourTypes: util.ArrayList[AnyRef] = new util.ArrayList[AnyRef]
    import scala.collection.JavaConversions._
    for (tourType <- tourTypes) {
      stringTourTypes.add(tourType.toString)
    }
    model.addAttribute("hotels", hotels)
    model.addAttribute("countries", countries)
    model.addAttribute("tourTypes", stringTourTypes)
    "create/new-tour"
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(value = Array("/reg-tour"))
  def regTour(@ModelAttribute tour: Tour): String = {
    tourService.insert(tour)
    "redirect:/tours"
  }
}
