package by.rekuts.travelagency.view.configuration

import by.rekuts.travelagency.service._
import by.rekuts.travelagency.view.controller._
import org.mockito.Mockito
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.multipart.support.StandardServletMultipartResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
class ViewTestConfig {
  @Bean
  def tourService: TourService = Mockito.mock(classOf[TourService])

  @Bean
  def countryService: CountryService = Mockito.mock(classOf[CountryService])

  @Bean
  def userService: UserService = Mockito.mock(classOf[UserService])

  @Bean
  def reviewService: ReviewService = Mockito.mock(classOf[ReviewService])

  @Bean
  def hotelService: HotelService = Mockito.mock(classOf[HotelService])

  @Bean
  def getController = new GetController(tourService, countryService, userService, reviewService, hotelService)

  @Bean
  def createController = new CreateController(userService, countryService, tourService, reviewService, hotelService)

  @Bean
  def deleteController = new DeleteController(reviewService, countryService, tourService, hotelService, userService)

  @Bean
  def updateController = new UpdateController(countryService, hotelService, reviewService, tourService, userService)

  @Bean
  def importController = new ImportController(tourService)

  @Bean
  def multipartResolver = new StandardServletMultipartResolver
}
