package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain._
import by.rekuts.travelagency.repository._
import by.rekuts.travelagency.service._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.{Model, ModelMap}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping}

@Controller
@ComponentScan(resourcePattern = "/styles", basePackages = Array("by.rekuts.**"))
class GetController @Autowired() (tourService: TourService, countryService: CountryService, userService: UserService, reviewService: ReviewService, hotelService: HotelService) {
  @Transactional
  @GetMapping(value = Array("/", "/tours"))
  def viewTours(model: ModelMap): String = {
    val tours: util.List[Tour] = tourService.getList(new TourSpecification)
    model.addAttribute("tours", tours)
    "list/tours"
  }

  @PreAuthorize("authentication.authenticated")
  @GetMapping(value = Array("/users"))
  def viewUsers(model: ModelMap): String = {
    val users: util.List[User] = userService.getList(new UserSpecification)
    model.addAttribute("users", users)
    "list/users"
  }

  @PreAuthorize("authentication.authenticated")
  @GetMapping(value = Array("/countries"))
  def viewCountries(model: ModelMap): String = {
    val countries: util.List[Country] = countryService.getList(new CountrySpecification)
    model.addAttribute("countries", countries)
    "list/countries"
  }

  @PreAuthorize("authentication.authenticated")
  @GetMapping(value = Array("/hotels"))
  def viewHotels(model: ModelMap): String = {
    val hotels: util.List[Hotel] = hotelService.getList(new HotelSpecification)
    model.addAttribute("hotels", hotels)
    "list/hotels"
  }

  @PreAuthorize("authentication.authenticated")
  @GetMapping(value = Array("/reviews"))
  def viewReviews(model: ModelMap): String = {
    val reviews: util.List[Review] = reviewService.getList(new ReviewSpecification)
    model.addAttribute("reviews", reviews)
    "list/reviews"
  }

  @PreAuthorize("authentication.authenticated")
  @Transactional
  @GetMapping(value = Array("/tours/{tourId}"))
  def getTour(@PathVariable("tourId") tourId: Int, model: Model): String = {
    val sessionUserId: Int = new ControllerHelper().getSessionUserId
    val tourSpecification: TourSpecification = new TourSpecification
    tourSpecification.userId_$eq(sessionUserId)
    val sesUserTours: util.List[Tour] = tourService.getList(tourSpecification)
    var isTourFavorite: Boolean = false
    import scala.collection.JavaConversions._
    for (tour <- sesUserTours) {
      if (tour.id == tourId) {
        isTourFavorite = true
      }
    }
    model.addAttribute("isFavorite", isTourFavorite)
    addTourAndReviewsToModel(tourId, model)
    "tour"
  }

  @PreAuthorize("authentication.authenticated")
  @Transactional
  @GetMapping(value = Array("/users/{userId}"))
  def getUser(@PathVariable("userId") userId: Int, model: Model): String = {
    val user: User = userService.getList(new UserSpecification(userId)).get(0)
    val specification: TourSpecification = new TourSpecification
    specification.userId_$eq(userId)
    val tours: util.List[Tour] = tourService.getList(specification)
    val reviewSpecification: ReviewSpecification = new ReviewSpecification
    reviewSpecification.userId_$eq(userId)
    val reviews: util.List[Review] = reviewService.getList(reviewSpecification)
    model.addAttribute("user", user)
    model.addAttribute("tours", tours)
    model.addAttribute("reviews", reviews)
    "user"
  }

  @PreAuthorize("authentication.authenticated")
  @Transactional
  @PostMapping(value = Array("/tours/{tourId}"))
  def getFavoriteTour(@PathVariable("tourId") tourId: Int, addToFavorites: Boolean, model: Model): String = {
    val sessionUserId: Int = new ControllerHelper().getSessionUserId
    val tourSpecification: TourSpecification = new TourSpecification
    tourSpecification.userId_$eq(sessionUserId)
    val sessionUser: User = userService.getList(new UserSpecification(sessionUserId)).get(0)
    if (addToFavorites) {
      val addedTour: Tour = tourService.getList(new TourSpecification(tourId)).get(0)
      userService.addTourToFavorites(sessionUser, addedTour)
    }
    else {
      val addedTour: Tour = tourService.getList(new TourSpecification(tourId)).get(0)
      userService.removeTourFromFavorites(sessionUser, addedTour)
    }
    addTourAndReviewsToModel(tourId, model)
    "redirect:/tours/{tourId}"
  }

  private def addTourAndReviewsToModel(tourId: Int, model: Model): Unit = {
    val tour: Tour = tourService.getList(new TourSpecification(tourId)).get(0)
    model.addAttribute("tour", tour)
    val specification: ReviewSpecification = new ReviewSpecification
    specification.tourId_$eq(tourId)
    val reviews: util.List[Review] = reviewService.getList(specification)
    model.addAttribute("reviews", reviews)
  }

  @PreAuthorize("authentication.authenticated")
  @Transactional
  @GetMapping(value = Array("/profile"))
  def getProfile(model: Model): String = {
    val sessionUserId: Int = new ControllerHelper().getSessionUserId
    val tourSpecification: TourSpecification = new TourSpecification
    tourSpecification.userId_$eq(sessionUserId)
    val tours: util.List[Tour] = tourService.getList(tourSpecification)
    val sessionUser: User = userService.getList(new UserSpecification(sessionUserId)).get(0)
    model.addAttribute("user", sessionUser)
    model.addAttribute("tours", tours)
    "user"
  }

  @PreAuthorize("authentication.authenticated")
  @Transactional
  @GetMapping(Array("/hotels/{hotelId}"))
  def getHotel(@PathVariable("hotelId") hotelId: Int, model: Model): String = {
    val hotel: Hotel = hotelService.getList(new HotelSpecification(hotelId)).get(0)
    model.addAttribute("hotel", hotel)
    "hotel"
  }
}

