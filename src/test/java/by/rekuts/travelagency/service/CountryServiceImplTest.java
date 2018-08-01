package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.service.impl.CountryServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CountryServiceImplTest {
    private List<Country> countries;

    @Before
    public void initializeCountriesList() {
        countries = Arrays.asList(
                new Country(1, "Russia"),
                new Country(2, "India"),
                new Country(3, "China"));
    }

    @Test
    public void insertTest(){
        CountryServiceImpl countryService = mock(CountryServiceImpl.class);
        doNothing().when(countryService).insert(new Country());
        countryService.insert(new Country());
        countryService.insert(new Country(3525, "Any Country"));
        verify(countryService, times(2)).insert(any());
    }

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
//        when(countryService.getCountryById(2)).thenReturn(countries.get(1));
//        Country country = countryService.getCountryById(2);
//        assertEquals("India", country.getName());
//    }
//
//    @Test
//    public void getCountryByIdTestFalse(){
//        CountryServiceImpl countryService = mock(CountryServiceImpl.class);
//        when(countryService.getCountryById(2)).thenReturn(countries.get(1));
//        Country country = countryService.getCountryById(2);
//        assertNotEquals("Russia", country.getName());
//    }

    @Test
    public void getAllCountriesTestTrue(){
        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
        when(countryServiceMock.getAllCountries()).thenReturn(countries);
        List<Country> countryList = countryServiceMock.getAllCountries();
        assertEquals(3, countryList.size());
    }

    @Test
    public void getAllCountriesTestFalse() {
        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
        when(countryServiceMock.getAllCountries()).thenReturn(countries);
        List<Country> countryList = countryServiceMock.getAllCountries();
        assertNotEquals(0, countryList.size());
    }
}
