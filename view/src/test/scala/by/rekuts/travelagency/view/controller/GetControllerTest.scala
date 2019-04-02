package by.rekuts.travelagency.view.controller

import java.util

import by.rekuts.travelagency.domain.{Hotel, Tour, User, UserRole}
import by.rekuts.travelagency.repository.{HotelSpecification, TourSpecification, UserSpecification}
import by.rekuts.travelagency.service.{HotelService, TourService, UserService}
import by.rekuts.travelagency.view.configuration.{TestSecurityConfiguration, ViewTestConfig}
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous
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
class GetControllerTest {
  @Autowired
  private var context: WebApplicationContext = _
  @Autowired
  private var tourService: TourService = _
  @Autowired
  private var userService: UserService = _
  @Autowired
  private var hotelService: HotelService = _

  private var mvc: MockMvc = _

  @Before
  def setup(): Unit = {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply[DefaultMockMvcBuilder](springSecurity)
      .alwaysDo[DefaultMockMvcBuilder](print())
      .build
  }

  private val users = new util.LinkedList[User]
  private val user = new User
  user.login_$eq("login")
  user.role_$eq(UserRole.ROLE_ADMIN)
  users.add(user)

  @Test
  @throws[Exception]
  def viewToursTest1(): Unit = {
    mvc.perform(get("/")).andExpect(view.name("list/tours"))
  }

  @Test(expected = classOf[AssertionError])
  @throws[Exception]
  def viewToursTestFalse(): Unit = {
    mvc.perform(get("/")).andExpect(view.name("list/countries"))
  }

  @Test
  @throws[Exception]
  def viewToursTest2(): Unit = {
    mvc.perform(get("/tours")).andExpect(view.name("list/tours"))
  }

  @Test
  @throws[Exception]
  def viewUsersTest(): Unit = {
    mvc.perform(get("/users").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(view.name("list/users"))
  }

  @Test
  @throws[Exception]
  def viewUsersTestFalse(): Unit = {
    mvc.perform(get("/users").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def viewCountriesTest(): Unit = {
    mvc.perform(get("/countries").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(view.name("list/countries"))
  }

  @Test
  @throws[Exception]
  def viewCountriesTestFalse(): Unit = {
    mvc.perform(get("/countries").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def viewHotelsTest(): Unit = {
    mvc.perform(get("/hotels").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(view.name("list/hotels"))
  }

  @Test
  @throws[Exception]
  def viewHotelsTestFalse(): Unit = {
    mvc.perform(get("/hotels").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def viewReviewsTest(): Unit = {
    mvc.perform(get("/reviews").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(view.name("list/reviews"))
  }

  @Test
  @throws[Exception]
  def viewReviewsTestFalse(): Unit = {
    mvc.perform(get("/reviews").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def getTourTest(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    val tours = util.Arrays.asList(new Tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(get("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(status.isOk).andExpect(forwardedUrl("tour")).andExpect(model.attribute("tour", tours.get(0)))
  }

  @Test
  @throws[Exception]
  def getTourTestFalse1(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    val tours = util.Arrays.asList(new Tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(get("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def getTourTestFalse2(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    val tours = util.Arrays.asList(new Tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(get("/tours/{tourId}", "tour").sessionAttr("sesUserId", 4).`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(status.isBadRequest)
  }

  @Test
  @throws[Exception]
  def getUserTest(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    mvc.perform(get("/users/{userId}", "178").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(status.isOk).andExpect(forwardedUrl("user")).andExpect(model.attribute("user", user))
  }

  @Test
  @throws[Exception]
  def getUserTestFalse(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    mvc.perform(get("/users/{userId}", "178").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def getFavoriteTourTest(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    val tours = util.Arrays.asList(new Tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(post("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(status.is3xxRedirection).andExpect(redirectedUrl("/tours/178")).andExpect(model.attribute("tour", tours.get(0)))
  }

  @Test
  @throws[Exception]
  def getFavoriteTourTestFalse(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    val tours = util.Arrays.asList(new Tour)
    when(tourService.getList(any(classOf[TourSpecification]))).thenReturn(tours)
    mvc.perform(post("/tours/{tourId}", "178").sessionAttr("sesUserId", 4).`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def getProfileTest(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    mvc.perform(get("/profile").sessionAttr("sesUserId", 4).`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(status.isOk).andExpect(forwardedUrl("user")).andExpect(model.attribute("user", user))
  }

  @Test
  @throws[Exception]
  def getProfileTestFalse(): Unit = {
    when(userService.getList(any(classOf[UserSpecification]))).thenReturn(users)
    mvc.perform(get("/profile").sessionAttr("sesUserId", 4).`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }

  @Test
  @throws[Exception]
  def getHotelTest(): Unit = {
    val hotels = util.Arrays.asList(new Hotel)
    when(hotelService.getList(any(classOf[HotelSpecification]))).thenReturn(hotels)
    mvc.perform(get("/hotels/{hotelId}", "6").`with`(SecurityMockMvcRequestPostProcessors.user("user"))).andExpect(view.name("hotel")).andExpect(model.attribute("hotel", hotels.get(0)))
  }

  @Test
  @throws[Exception]
  def getHotelTestFalse(): Unit = {
    val hotels = util.Arrays.asList(new Hotel)
    when(hotelService.getList(any(classOf[HotelSpecification]))).thenReturn(hotels)
    mvc.perform(get("/hotels/{hotelId}", "8").`with`(anonymous)).andExpect(status.isFound).andExpect(redirectedUrl("http://localhost/sign-in"))
  }
}
