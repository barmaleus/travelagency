package by.rekuts.travelagency.repository.impl

import java.io.{IOException, StringReader}
import java.time.ZoneId
import java.util

import by.rekuts.travelagency.domain.TourType.TourType
import by.rekuts.travelagency.domain.{CsvTour, Hotel, Tour, TourType}
import by.rekuts.travelagency.repository._
import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}
import javax.persistence.{EntityManager, PersistenceContext}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CSVTourLoaderImpl @Autowired() (countryRepository: CountryRepository, hotelRepository: HotelRepository) extends CSVTourLoader {
  @PersistenceContext
  var entityManager: EntityManager = _

  override def importCsvTours(csvFilePath: String): Unit = {
    var csvTours: util.List[CsvTour] = null
    try
      csvTours = importCsvToCsvTourList(csvFilePath)
    catch {
      case e: IOException =>
        CSVTourLoaderImpl.log.warn("The csv file not found on path " + csvFilePath, e)
    }
    insertCsvToursToDatabase(csvTours)
  }

  @throws[IOException]
  private def importCsvToCsvTourList(csvFileContent: String): util.List[CsvTour] = {
    val csvTours: util.List[CsvTour] = new util.ArrayList[CsvTour]
      val reader: StringReader = new StringReader(csvFileContent)
      try {
        val csvToBean: CsvToBean[CsvTour] = new CsvToBeanBuilder[CsvTour](reader).withType(classOf[CsvTour]).withSkipLines(1).withIgnoreLeadingWhiteSpace(true).build
        import scala.collection.JavaConversions._
        for (csvTour <- csvToBean) {
          csvTours.add(csvTour)
        }
      } finally if (reader != null) reader.close()
    csvTours
  }

  private def insertCsvToursToDatabase(csvTours: util.List[CsvTour]): Unit = {
    import scala.collection.JavaConversions._
    for (csvTour <- csvTours) {
      val tour: Tour = parseCsvTourToTour(csvTour)
      entityManager.persist(tour)
    }
  }

  private def parseCsvTourToTour(csvTour: CsvTour): Tour = {
    val tour: Tour = new Tour
    tour.photo_$eq(csvTour.photo)
    tour.date_$eq(csvTour.date.toInstant.atZone(ZoneId.systemDefault).toLocalDate)
    tour.duration_$eq(csvTour.duration)
    tour.description_$eq(csvTour.description)
    tour.cost_$eq(csvTour.cost)
    var tourType: TourType = null
    for (value <- TourType.values) {
      if (value.id == csvTour.tourTypeId) {
        tourType = value
      }
    }
    tour.tourType_$eq(tourType)
    val hotelSpecification: HotelSpecification = new HotelSpecification(csvTour.hotelId)
    val hotel: Hotel = hotelRepository.getList(hotelSpecification).get(0)
    tour.hotel_$eq(hotel)
    tour.country_$eq(countryRepository.getList(new CountrySpecification(csvTour.countryId)).get(0))
    tour
  }

  object CSVTourLoaderImpl {
    val log: Logger = LoggerFactory.getLogger(classOf[CSVTourLoaderImpl])
  }
}
