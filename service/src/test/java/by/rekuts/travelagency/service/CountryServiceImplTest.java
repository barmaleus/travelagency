//package by.rekuts.travelagency.logic.service;
//
//import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
//import by.rekuts.travelagency.repository.CountrySpecification;
//import by.rekuts.travelagency.domain.Country;
//import by.rekuts.travelagency.service.impl.CountryServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class CountryServiceImplTest {
//
//    private List<Country> countries;
//    @Before
//    public void initializeCountriesList() {
//        countries = Arrays.asList(
//                new Country(1, "Russia"),
//                new Country(2, "India"),
//                new Country(3, "China"));
//    }
//
//    @Test
//    public void insertCountryMockTest(){
//        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
//        doNothing().when(countryServiceMock).insert(new Country());
//        countryServiceMock.insert(new Country());
//        countryServiceMock.insert(new Country(3525, "Any Country"));
//        verify(countryServiceMock, times(2)).insert(any());
//    }
//
//    @Test
//    public void deleteTest() {
//        CountryServiceImpl countryService = mock(CountryServiceImpl.class);
//        doNothing().when(countryService).insert(new Country());
//        countryService.delete(1);
//        countryService.insert(new Country(3525, "Any Country"));
//        verify(countryService, times(1)).delete(anyInt());
//    }
//
//    @Test
//    public void getCountryByIdTestTrue(){
//        CountryServiceImpl countryService = mock(CountryServiceImpl.class);
//        CountrySpecification specification = new CountrySpecification(2);
//        List<Country> singletonList = Collections.singletonList(countries.get(1));
//        when(countryService.getList(specification)).thenReturn(singletonList);
//        Country country = countryService.getList(specification).get(0);
//        assertEquals("India", country.getName());
//    }
//
//    @Test
//    public void getCountryByIdTestFalse(){
//        CountryServiceImpl countryService = mock(CountryServiceImpl.class);
//        CountrySpecification specification = new CountrySpecification(2);
//        List<Country> singletonList = Collections.singletonList(countries.get(1));
//        when(countryService.getList(specification)).thenReturn(singletonList);
//        Country country = countryService.getList(specification).get(0);
//        assertNotEquals("Russia", country.getName());
//    }
//
//    @Test
//    public void getAllCountriesTestTrue(){
//        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
//        CountrySpecification specification = new CountrySpecification();
//        when(countryServiceMock.getList(specification)).thenReturn(countries);
//        List<Country> countryList = countryServiceMock.getList(specification);
//        assertEquals(3, countryList.size());
//    }
//
//    @Test
//    public void getAllCountriesTestFalse() {
//        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
//        CountrySpecification specification = new CountrySpecification();
//        when(countryServiceMock.getList(specification)).thenReturn(countries);
//        List<Country> countryList = countryServiceMock.getList(specification);
//        assertNotEquals(0, countryList.size());
//    }
//}
