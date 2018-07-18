package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.impl.CountryServiceImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryServiceImplTest {

    @Test
    @Ignore
    public void insertTest() throws SQLException {
//        List<Country> countries = Arrays.asList(
//                new Country(1, "Russia"),
//                new Country(2, "India"),
//                new Country(3, "China"));
//
//        int count = 0;
//
//        CountryDaoImpl spy = Mockito.spy(CountryDaoImpl.class);
//        Mockito.doReturn(count++).when(spy.insert(any(Country.class)));
//
//        CountryServiceImpl countryService = new CountryServiceImpl();
//        countryService.insert(new Country());
//        countryService.insert(new Country());
//
//        assertEquals(2, count);
    }

    @Test
    @Ignore
    public void deleteTestTrue() throws SQLException{

    }

    @Test
    @Ignore
    public void deleteTestFalse() throws SQLException{

    }

    @Test
    @Ignore
    public void getCountryByIdTestTrue() throws SQLException {

    }

    @Test
    @Ignore
    public void getCountryByIdTestFalse() throws SQLException {

    }

    @Test
    @Ignore
    public void getAllCountriesTestTrue() throws SQLException {
        List<Country> countries = Arrays.asList(
                new Country(1, "Russia"),
                new Country(2, "India"),
                new Country(3, "China"));
        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
        when(countryServiceMock.getAllCountries()).thenReturn(countries);

        CountryServiceImpl countryService = countryServiceMock;
        List<Country> countryList = countryService.getAllCountries();
        assertEquals(3, countryList.size());
    }

    @Test
    @Ignore
    public void getAllCountriesTestFalse() throws SQLException {
        List<Country> countries = Arrays.asList(
                new Country(1, "Russia"),
                new Country(2, "India"),
                new Country(3, "China"));
        CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);
        when(countryServiceMock.getAllCountries()).thenReturn(countries);

        CountryServiceImpl countryService = countryServiceMock;
        List<Country> countryList = countryService.getAllCountries();
        assertNotEquals(0, countryList.size());
    }
}
