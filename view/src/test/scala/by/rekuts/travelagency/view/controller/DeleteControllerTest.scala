package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain.{Country, Hotel, Tour}
import by.rekuts.travelagency.repository.TourSpecification
import by.rekuts.travelagency.service.TourService
import by.rekuts.travelagency.view.configuration.{TestSecurityConfiguration, ViewTestConfig}
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.{anonymous, user}
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{forwardedUrl, redirectedUrl, status, view}
import org.springframework.test.web.servlet.setup.{DefaultMockMvcBuilder, MockMvcBuilders}
import org.springframework.web.context.WebApplicationContext

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[ViewTestConfig], classOf[TestSecurityConfiguration]))
@WebAppConfiguration
@ComponentScan(basePackages = Array("by.rekuts.**"))
class DeleteControllerTest {
  @Autowired
  private var context: WebApplicationContext = _
  @Autowired
  private var tourService: TourService = _

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
  def delReviewTest(): Unit = {
    mvc.perform(post("/del-review").param("reviewId", "123").`with`(user("user")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def delReviewTestFalse1(): Unit = {
    mvc.perform(post("/del-review").param("reviewId", "123").`with`(anonymous))
      .andExpect(status.isFound)
      .andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def delReviewTestFalse2(): Unit = {
    mvc.perform(post("/del-review").param("reviewId", "review").`with`(user("user")))
      .andExpect(status.isBadRequest)
  }

  //todo this test works aalone, but doesn't pass with other tests
//  @Test
//  @throws[Exception]
//  def delCountryTest(): Unit = {
//    mvc.perform(post("/del-country").param("countryId", "123").`with`(user("user").roles("ADMIN"))).andExpect(status.isFound).andExpect(view.name("redirect:/countries"))
//  }

  @Test
  @throws[Exception]
  def delCountryTestFalse1(): Unit = {
    val country = new Country
    country.id_$eq(123)
    val tour = new Tour
    tour.country_$eq(country)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(post("/del-country").param("countryId", "123").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def delCountryTestFalse2(): Unit = {
    mvc.perform(post("/del-country").param("countryId", "123").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def delCountryTestFalse3(): Unit = {
    mvc.perform(post("/del-country").param("countryId", "UAE").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def delHotelTest(): Unit = {
    mvc.perform(post("/del-hotel").param("hotelId", "555").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/hotels"))
  }

  @Test
  @throws[Exception]
  def delHotelsTestFalse1(): Unit = {
    val hotel = new Hotel
    hotel.id_$eq(123)
    val tour = new Tour
    tour.hotel_$eq(hotel)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(post("/del-hotel").param("hotelId", "123").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def delHotelTestFalse2(): Unit = {
    mvc.perform(post("/del-hotel").param("hotelId", "123").`with`(user("user"))).andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def delHotelTestFalse3(): Unit = {
    mvc.perform(post("/del-hotel").param("hotelId", "Tourist").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def delTourTest(): Unit = {
    mvc.perform(post("/del-tour").param("tourId", "123").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def delTourTestFalse1(): Unit = {
    mvc.perform(post("/del-tour").param("tourId", "123").`with`(anonymous)).andExpect(status.isFound)
      .andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def delTourTestFalse2(): Unit = {
    mvc.perform(post("/del-tour").param("tourId", "tour").`with`(user("user")))
      .andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def delUserTest(): Unit = {
    mvc.perform(post("/del-user").param("userId", "123").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/users"))
  }

  @Test
  @throws[Exception]
  def delUserTestFalse1(): Unit = {
    mvc.perform(post("/del-user").param("userId", "123").`with`(anonymous)).andExpect(status.isFound)
      .andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def delUserTestFalse2(): Unit = {
    mvc.perform(post("/del-user").param("userId", "user").`with`(user("user")))
      .andExpect(status.isBadRequest)
  }
}
