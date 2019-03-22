package by.rekuts.travelagency.service.mocktests;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.service.impl.CountryServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CountryServiceImplTest {
    private Country country = new Country();

    private List<Country> countries;

    private CountryServiceImpl countryServiceMock = mock(CountryServiceImpl.class);

    @Before
    public void initializeCountriesList() {
        country.setId(3525);
        country.setName("Any Country");

        var country1 = new Country();
        country1.setId(1);
        country1.setName("Russia");
        var country2 = new Country();
        country2.setId(2);
        country2.setName("India");
        var country3 = new Country();
        country3.setId(3);
        country3.setName("China");

        countries = List.of(country1, country2, country3);
    }

    @Test
    public void insertCountryMockTest(){
        doNothing().when(countryServiceMock).insert(new Country());
        countryServiceMock.insert(new Country());
        countryServiceMock.insert(country);
        verify(countryServiceMock, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        doNothing().when(countryServiceMock).insert(new Country());
        countryServiceMock.delete(1);
        countryServiceMock.insert(country);
        verify(countryServiceMock, times(1)).delete(anyInt());
    }

    @Test
    public void getCountryByIdTestTrue(){
        var specification = new CountrySpecification(2);
        var singletonList = List.of(countries.get(1));
        when(countryServiceMock.getList(specification)).thenReturn(singletonList);
        var country = countryServiceMock.getList(specification).get(0);
        assertEquals("India", country.getName());
    }

    @Test
    public void getCountryByIdTestFalse(){
        var specification = new CountrySpecification(2);
        var singletonList = List.of(countries.get(1));
        when(countryServiceMock.getList(specification)).thenReturn(singletonList);
        var country = countryServiceMock.getList(specification).get(0);
        assertNotEquals("Russia", country.getName());
    }

    @Test
    public void getAllCountriesTestTrue(){
        var specification = new CountrySpecification();
        when(countryServiceMock.getList(specification)).thenReturn(countries);
        var countryList = countryServiceMock.getList(specification);
        assertEquals(3, countryList.size());
    }

    @Test
    public void getAllCountriesTestFalse() {
        var specification = new CountrySpecification();
        when(countryServiceMock.getList(specification)).thenReturn(countries);
        var countryList = countryServiceMock.getList(specification);
        assertNotEquals(0, countryList.size());
    }
}
