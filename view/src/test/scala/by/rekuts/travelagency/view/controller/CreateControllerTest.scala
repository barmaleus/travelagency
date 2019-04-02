package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain.Tour
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.{get, post}
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import org.springframework.test.web.servlet.setup.{DefaultMockMvcBuilder, MockMvcBuilders}
import org.springframework.web.context.WebApplicationContext

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[ViewTestConfig], classOf[TestSecurityConfiguration]))
@WebAppConfiguration
@ComponentScan(basePackages = Array("by.rekuts.**"))
class CreateControllerTest {
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
  def signUpTest(): Unit = {
    mvc.perform(post("/reg-user").`with`(anonymous))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def signUpTestFalse(): Unit = {
    mvc.perform(post("/reg-user").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def regCountryTest(): Unit = {
    mvc.perform(post("/reg-country").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/countries"))
  }

  @Test
  @throws[Exception]
  def regCountryTestFalse(): Unit = {
    mvc.perform(post("/reg-country").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def newReviewTest(): Unit = {
    val tour = new Tour
    tour.id_$eq(6)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).`with`(user("user").roles("ADMIN")))
      .andExpect(view.name("create/new-review"))
  }

  @Test
  @throws[Exception]
  def newReviewTestFalse1(): Unit = {
    val tour = new Tour
    tour.id_$eq(6)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(get("/new-review").`with`(anonymous))
      .andExpect(status.isFound)
      .andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def newReviewTestFalse2(): Unit = {
    val tour = new Tour
    tour.id_$eq(6)
    val tours = util.Arrays.asList(tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(get("/new-review").sessionAttr("sesUserId", 4).`with`(user("user").roles("MEMBER")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def newUserReviewTestTrue1(): Unit = {
    mvc.perform(get("/tours/{tourId}/add-review", "6").sessionAttr("sesUserId", 4).`with`(user("user").roles("ADMIN")))
      .andExpect(view.name("create/new-review"))
  }

  @Test
  @throws[Exception]
  def newUserReviewTestTrue2(): Unit = {
    mvc.perform(get("/tours/{tourId}/add-review", "6").sessionAttr("sesUserId", 4).`with`(user("user").roles("MEMBER")))
      .andExpect(view.name("create/new-review"))
  }

  @Test
  @throws[Exception]
  def newUserReviewTestFalse(): Unit = {
    mvc.perform(get("/tours/{tourId}/add-review", "6").sessionAttr("sesUserId", 4).`with`(anonymous))
      .andExpect(status.isFound)
      .andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def regReviewTestTrue1(): Unit = {
    mvc.perform(post("/reg-review").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def regReviewTestTrue2(): Unit = {
    mvc.perform(post("/reg-review").`with`(user("user")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def regReviewTestFalse1_anonymous(): Unit = {
    mvc.perform(post("/reg-review").`with`(anonymous))
      .andExpect(status.isFound)
      .andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def newHotelTest(): Unit = {
    mvc.perform(get("/new-hotel").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isOk).andExpect(forwardedUrl("create/new-hotel"))
      .andExpect(view.name("create/new-hotel"))
  }

  @Test
  @throws[Exception]
  def newHotelTestFalse(): Unit = {
    mvc.perform(get("/new-hotel").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def regHotelTest(): Unit = {
    mvc.perform(post("/reg-hotel").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/hotels"))
  }

  @Test
  @throws[Exception]
  def regHotelTestFalse(): Unit = {
    mvc.perform(post("/reg-hotel").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def newTourTest(): Unit = {
    mvc.perform(get("/new-tour").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isOk)
      .andExpect(model.attributeExists("hotels", "countries", "tourTypes"))
      .andExpect(forwardedUrl("create/new-tour"))
      .andExpect(view.name("create/new-tour"))
  }

  @Test
  @throws[Exception]
  def newTourTestFalse(): Unit = {
    mvc.perform(get("/new-tour").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }

  @Test
  @throws[Exception]
  def regTourTestTrue(): Unit = {
    mvc.perform(post("/reg-tour").`with`(user("user").roles("ADMIN")))
      .andExpect(status.isFound)
      .andExpect(view.name("redirect:/tours"))
  }

  @Test
  @throws[Exception]
  def regTourTestTrueFalse(): Unit = {
    mvc.perform(post("/reg-tour").`with`(user("user")))
      .andExpect(status.isForbidden)
      .andExpect(forwardedUrl("/error"))
  }
}
