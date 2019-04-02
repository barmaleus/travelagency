package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain._
import by.rekuts.travelagency.repository.Specification
import by.rekuts.travelagency.service._
import by.rekuts.travelagency.view.configuration.{TestSecurityConfiguration, ViewTestConfig}
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.{anonymous, user}
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.{get, post}
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{forwardedUrl, redirectedUrl, status, view}
import org.springframework.test.web.servlet.setup.{DefaultMockMvcBuilder, MockMvcBuilders}
import org.springframework.web.context.WebApplicationContext

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[ViewTestConfig], classOf[TestSecurityConfiguration]))
@WebAppConfiguration
@ComponentScan(basePackages = Array("by.rekuts.**"))
class UpdateControllerTest {
  @Autowired
  private var context: WebApplicationContext = _
  @Autowired
  private var tourService: TourService = _
  @Autowired
  private var userService: UserService = _
  @Autowired
  private var hotelService: HotelService = _
  @Autowired
  private var countryService: CountryService = _
  @Autowired
  private var reviewService: ReviewService = _

  private var mvc: MockMvc = _

  @Before
  def setup(): Unit = {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply[DefaultMockMvcBuilder](springSecurity)
      .alwaysDo[DefaultMockMvcBuilder](print())
      .build
  }

  @Test
  @throws[Exception]
  def updateCountryTest_post(): Unit = {
    mvc.perform(post("/update-country").`with`(user("user").roles("ADMIN"))).andExpect(status.isFound).andExpect(view.name("redirect:/countries"))
  }

  @Test
  @throws[Exception]
  def updateCountryTestFalse_post(): Unit = {
    mvc.perform(post("/update-country").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateCountryTest_get(): Unit = {
    val country = new Country
    country.id_$eq(123)
    val countries = util.Arrays.asList(country)
    when(countryService.getList(any(classOf[Specification[_]]))).thenReturn(countries)
    mvc.perform(get("/countries/{countryId}/update", "123").`with`(user("user").roles("ADMIN"))).andExpect(view.name("update/update-country"))
  }

  @Test
  @throws[Exception]
  def updateCountryTestFalse1_get(): Unit = {
    val country = new Country
    country.id_$eq(123)
    val countries = util.Arrays.asList(country)
    when(countryService.getList(any(classOf[Specification[_]]))).thenReturn(countries)
    mvc.perform(get("/countries/{countryId}/update", "123").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateCountryTestFalse2_get(): Unit = {
    val country = new Country
    country.id_$eq(123)
    val countries = util.Arrays.asList(country)
    when(countryService.getList(any(classOf[Specification[_]]))).thenReturn(countries)
    mvc.perform(get("/countries/{countryId}/update", "t34").`with`(user("user"))).andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def updateHotelTest_post(): Unit = {
    mvc.perform(post("/update-hotel").`with`(user("user").roles("ADMIN"))).andExpect(status.isFound).andExpect(view.name("redirect:/hotels"))
  }

  @Test
  @throws[Exception]
  def updateHotelTestFalse_post(): Unit = {
    mvc.perform(post("/update-hotel").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateHotelTest_get(): Unit = {
    val hotel = new Hotel
    hotel.id_$eq(123)
    val hotels = util.Arrays.asList(hotel)
    when(hotelService.getList(any(classOf[Specification[_]]))).thenReturn(hotels)
    mvc.perform(get("/hotels/{hotelId}/update", "123").`with`(user("user").roles("ADMIN"))).andExpect(view.name("update/update-hotel"))
  }

  @Test
  @throws[Exception]
  def updateHotelTestFalse1_get(): Unit = {
    val hotel = new Hotel
    hotel.id_$eq(123)
    val hotels = util.Arrays.asList(hotel)
    when(hotelService.getList(any(classOf[Specification[_]]))).thenReturn(hotels)
    mvc.perform(get("/hotels/{hotelId}/update", "123").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateHotelTestFalse2_get(): Unit = {
    val hotel = new Hotel
    hotel.id_$eq(123)
    val hotels = util.Arrays.asList(hotel)
    when(hotelService.getList(any(classOf[Specification[_]]))).thenReturn(hotels)
    mvc.perform(get("/hotels/{hotelId}/update", "t34").`with`(user("user"))).andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def updateReviewTest_post(): Unit = {
    mvc.perform(post("/update-review").`with`(user("user"))).andExpect(status.isFound).andExpect(view.name("redirect:/reviews"))
  }

  @Test
  @throws[Exception]
  def updateReviewTestFalse_post(): Unit = {
    mvc.perform(post("/update-review").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def updateReviewTest_get(): Unit = {
    val review = new Review
    review.id_$eq(123)
    val reviews = util.Arrays.asList(review)
    when(reviewService.getList(any(classOf[Specification[_]]))).thenReturn(reviews)
    mvc.perform(get("/reviews/{reviewId}/update", "123").`with`(user("user"))).andExpect(view.name("update/update-review"))
  }

  @Test
  @throws[Exception]
  def updateReviewTestFalse1_get(): Unit = {
    val review = new Review
    review.id_$eq(123)
    val reviews = util.Arrays.asList(review)
    when(reviewService.getList(any(classOf[Specification[_]]))).thenReturn(reviews)
    mvc.perform(get("/reviews/{reviewId}/update", "123").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def updateReviewTestFalse2_get(): Unit = {
    val review = new Review
    review.id_$eq(123)
    val reviews = util.Arrays.asList(review)
    when(reviewService.getList(any(classOf[Specification[_]]))).thenReturn(reviews)
    mvc.perform(get("/countries/{countryId}/update", "t34").`with`(user("user"))).andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def updateTourTest_post(): Unit = {
    mvc.perform(post("/update-tour").`with`(user("user").roles("ADMIN"))).andExpect(status.isFound).andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def updateTourTestFalse_post(): Unit = {
    mvc.perform(post("/update-tour").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateTourTest_get(): Unit = {
    val tour = new Tour
    tour.id_$eq(123)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[Specification[_]]))).thenReturn(tours)
    mvc.perform(get("/tours/{tourId}/update", "123").`with`(user("user").roles("ADMIN"))).andExpect(view.name("update/update-tour"))
  }

  @Test
  @throws[Exception]
  def updateTourTestFalse1_get(): Unit = {
    val tour = new Tour
    tour.id_$eq(123)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[Specification[_]]))).thenReturn(tours)
    mvc.perform(get("/tours/{tourId}/update", "123").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateTourTestFalse2_get(): Unit = {
    val tour = new Tour
    tour.id_$eq(123)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[Specification[_]]))).thenReturn(tours)
    mvc.perform(get("/tours/{tourId}/update", "t34").`with`(user("user").roles("ADMIN"))).andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def updateUserTest_post(): Unit = {
    mvc.perform(post("/update-user").`with`(user("user").roles("ADMIN"))).andExpect(status.isFound).andExpect(view.name("redirect:/users"))
  }

  @Test
  @throws[Exception]
  def updateUserTestFalse_post(): Unit = {
    mvc.perform(post("/update-tour").`with`(user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateUserTest_get(): Unit = {
    val user = new User
    user.id_$eq(123)
    val users = util.Arrays.asList(user)
    when(userService.getList(any(classOf[Specification[_]]))).thenReturn(users)
    mvc.perform(get("/users/{userId}/update", "123").`with`(SecurityMockMvcRequestPostProcessors.user("user").roles("ADMIN"))).andExpect(view.name("update/update-user"))
  }

  @Test
  @throws[Exception]
  def updateUserTestFalse1_get(): Unit = {
    val user = new User
    user.id_$eq(123)
    val users = util.Arrays.asList(user)
    when(userService.getList(any(classOf[Specification[_]]))).thenReturn(users)
    mvc.perform(get("/users/{userId}/update", "123").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(status.isForbidden).andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def updateUserTestFalse2_get(): Unit = {
    val user = new User
    user.id_$eq(123)
    val users = util.Arrays.asList(user)
    when(userService.getList(any(classOf[Specification[_]]))).thenReturn(users)
    mvc.perform(get("/users/{userId}/update", "t34").`with`(SecurityMockMvcRequestPostProcessors.user("user").roles("ADMIN"))).andExpect(status.isBadRequest)
  }
}
