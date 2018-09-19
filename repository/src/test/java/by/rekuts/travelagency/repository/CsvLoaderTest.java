//package by.rekuts.travelagency.repository;
//
//import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
//import by.rekuts.travelagency.repository.impl.CSVTourLoaderImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//@Slf4j
//public class CsvLoaderTest {
//
//    @Autowired
//    CSVTourLoaderImpl csvLoader;
//    @Autowired
//    TourRepository tourRepository;
//
//
//    @Test
//    public void parseAndInsertCSVToursTestTrue() throws IOException {
//        String SAMPLE_CSV_FILE_CONTENT =
//                "photo, date, duration, description, cost, tour_type_id, hotelId, counry_id\n" +
//                        "\"https://images.unsplash.com/photo-1534865244288-b47fca3a35e0\", 15-08-2018, 7, \"some description\", 500.8457, 3, 2, 2\n" +
//                        "\"https://images.unsplash.com/photo-1534960583798-8af0getc136d88c\", 31-09-2019, 14, \"another description\", 390, 7, 3, 4";
//
//        int beginToursAmount = tourRepository.getList(new TourSpecification()).size();
//        csvLoader.importCsvTours(SAMPLE_CSV_FILE_CONTENT);
//        int endToursAmount = tourRepository.getList(new TourSpecification()).size();
//        Assert.assertEquals(2, endToursAmount - beginToursAmount);
//    }
//}