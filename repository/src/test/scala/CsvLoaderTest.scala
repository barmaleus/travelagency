import java.io.IOException

import by.rekuts.travelagency.repository.{TourRepository, TourSpecification}
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.repository.impl.CSVTourLoaderImpl
import org.junit.{Assert, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class CsvLoaderTest {
  @Autowired
  private var csvLoader: CSVTourLoaderImpl = _
  @Autowired
  private var tourRepository: TourRepository = _

  @Test
  @throws[IOException]
  def parseAndInsertCSVToursTestTrue(): Unit = {
    val SAMPLE_CSV_FILE_CONTENT = "photo, date, duration, description, cost, tour_type_id, hotelId, counry_id\n" + "\"https://images.unsplash.com/photo-1534865244288-b47fca3a35e0\", 15-08-2018, 7, \"some description\", 500.8457, 3, 2, 2\n" + "\"https://images.unsplash.com/photo-1534960583798-8af0getc136d88c\", 31-09-2019, 14, \"another description\", 390, 7, 3, 4"
    val beginToursAmount = tourRepository.getList(new TourSpecification).size
    csvLoader.importCsvTours(SAMPLE_CSV_FILE_CONTENT)
    val endToursAmount = tourRepository.getList(new TourSpecification).size
    Assert.assertEquals(2, endToursAmount - beginToursAmount)
  }
}
