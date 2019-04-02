package by.rekuts.travelagency.domain

import java.math.BigDecimal
import java.util.Date

import com.opencsv.bean.{CsvBindByPosition, CsvDate}

class CsvTour {
  @CsvBindByPosition(position = 0, required = true)
  var photo: String = _

  @CsvBindByPosition(position = 1, required = true)
  @CsvDate("dd-MM-yyyy")
  var date: Date = _

  @CsvBindByPosition(position = 2, required = true)
  var duration: Int = _

  @CsvBindByPosition(position = 3, required = true)
  var description: String = _

  @CsvBindByPosition(position = 4, required = true)
  var cost: BigDecimal = _

  @CsvBindByPosition(position = 5, required = true)
  var tourTypeId: Int = _

  @CsvBindByPosition(position = 6, required = true)
  var hotelId: Int = _

  @CsvBindByPosition(position = 7, required = true)
  var countryId: Int = _
}
