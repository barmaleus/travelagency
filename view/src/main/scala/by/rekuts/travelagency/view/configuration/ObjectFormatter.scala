package by.rekuts.travelagency.view.configuration

import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

import by.rekuts.travelagency.domain.{Country, Hotel, Tour, User}
import by.rekuts.travelagency.repository.{CountrySpecification, HotelSpecification, TourSpecification, UserSpecification}
import by.rekuts.travelagency.service.{CountryService, HotelService, TourService, UserService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.Formatter
import org.springframework.stereotype.Component

@Component
class ObjectFormatter {
  @Autowired
  private var tourService: TourService = _
  @Autowired
  private var userService: UserService = _
  @Autowired
  private var countryService: CountryService = _
  @Autowired
  private var hotelService: HotelService = _

  private[configuration] def localDateFormatter: Formatter[LocalDate] = new Formatter[LocalDate]() {
    @throws[ParseException]
    override def parse(text: String, locale: Locale): LocalDate = return LocalDate.parse(text, DateTimeFormatter.ISO_DATE)

    override def print(`object`: LocalDate, locale: Locale): String = return DateTimeFormatter.ISO_DATE.format(`object`)
  }

  private[configuration] def tourFormatter: Formatter[Tour] = new Formatter[Tour]() {
    @throws[ParseException]
    override def parse(tourId: String, locale: Locale): Tour = tourService.getList(new TourSpecification(tourId.toInt)).get(0)

    override def print(tour: Tour, locale: Locale): String = return tour.toString
  }

  private[configuration] def userFormatter: Formatter[User] = new Formatter[User]() {
    @throws[ParseException]
    override def parse(userId: String, locale: Locale): User = userService.getList(new UserSpecification(userId.toInt)).get(0)

    override def print(user: User, locale: Locale): String = return user.toString
  }

  private[configuration] def countryFormatter: Formatter[Country] = new Formatter[Country]() {
    @throws[ParseException]
    override def parse(countryId: String, locale: Locale): Country = countryService.getList(new CountrySpecification(countryId.toInt)).get(0)

    override def print(country: Country, locale: Locale): String = return country.toString
  }

  private[configuration] def hotelFormatter = new Formatter[Hotel]() {
    @throws[ParseException]
    override def parse(hotelId: String, locale: Locale): Hotel = hotelService.getList(new HotelSpecification(hotelId.toInt)).get(0)

    override def print(hotel: Hotel, locale: Locale): String = return hotel.toString
  }
}
