package by.rekuts.travelagency.service.mocktests;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class HotelServiceImplTest {
    private List<Hotel> hotels;

    @Before
    public void initializeHotelsList() {
        var hotel1 = new Hotel();
        hotel1.setId(1);
        hotel1.setName("Tourist");
        hotel1.setStars(3);
        hotel1.setWebsite("tourist.by");
        hotel1.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel1.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel1.setFeatures(new ArrayList<>());
        var hotel2 = new Hotel();
        hotel2.setId(2);
        hotel2.setName("Red Dragon");
        hotel2.setStars(3);
        hotel2.setWebsite("redragon.cn");
        hotel2.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel2.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel2.setFeatures(new ArrayList<>());
        var hotel3 = new Hotel();
        hotel3.setId(3);
        hotel3.setName("Little Italia");
        hotel3.setStars(3);
        hotel3.setWebsite("l-italia.by");
        hotel3.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel3.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel3.setFeatures(new ArrayList<>());
        hotels = List.of(hotel1, hotel2, hotel3);
    }

    @Test
    public void insertTest(){
        var hotelService = mock(HotelServiceImpl.class);
        doNothing().when(hotelService).insert(any(Hotel.class));
        hotelService.insert(any(Hotel.class));
        hotelService.insert(any(Hotel.class));
        verify(hotelService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        var hotelService = mock(HotelServiceImpl.class);
        doNothing().when(hotelService).insert(any(Hotel.class));
        hotelService.delete(6);
        hotelService.delete(8);
        verify(hotelService, times(2)).delete(anyInt());
    }

    @Test
    public void getHotelByIdTestTrue() {
        var hotelService = mock(HotelServiceImpl.class);
        var specification = new HotelSpecification(2);
        var singletonList = List.of(hotels.get(1));
        when(hotelService.getList(specification)).thenReturn(singletonList);
        var hotel = hotelService.getList(specification).get(0);
        assertEquals("Red Dragon", hotel.getName());
    }

    @Test
    public void getHotelByIdTestFalse() {
        var hotelService = mock(HotelServiceImpl.class);
        var specification = new HotelSpecification(2);
        var singletonList = List.of(hotels.get(1));
        when(hotelService.getList(specification)).thenReturn(singletonList);
        var hotel = hotelService.getList(specification).get(0);
        assertNotEquals("Tourist", hotel.getName());
    }

    @Test
    public void getAllHotelsTestTrue() {
        var hotelServiceMock = mock(HotelServiceImpl.class);
        var specification = new HotelSpecification();
        when(hotelServiceMock.getList(specification)).thenReturn(hotels);
        var hotelList = hotelServiceMock.getList(specification);
        assertEquals(3, hotelList.size());
    }

    @Test
    public void getAllHotelsTestFalse() {
        var hotelServiceMock = mock(HotelServiceImpl.class);
        var specification = new HotelSpecification();
        when(hotelServiceMock.getList(specification)).thenReturn(hotels);
        var hotelList = hotelServiceMock.getList(specification);
        assertNotEquals(0, hotelList.size());
    }
}
