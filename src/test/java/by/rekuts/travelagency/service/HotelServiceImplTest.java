package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Hotel;
import by.rekuts.travelagency.service.impl.HotelServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class HotelServiceImplTest {
    @Test
    public void insertTest(){
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        doNothing().when(hotelService).insert(new Hotel());
        hotelService.insert(new Hotel());
        hotelService.insert(new Hotel());
        verify(hotelService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        doNothing().when(hotelService).insert(new Hotel());
        hotelService.delete(6);
        hotelService.delete(8);
        verify(hotelService, times(2)).delete(anyInt());
    }

    @Test
    public void getHotelByIdTestTrue() {
        List<Hotel> hotels = Arrays.asList(
                new Hotel(1, "Tourist", 3, "tourist.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(2, "Red Dragon", 3, "redragon.cn", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(3, "Little Italia", 3, "l-italia.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()));
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        when(hotelService.getHotelById(2)).thenReturn(hotels.get(1));
        Hotel hotel = hotelService.getHotelById(2);
        assertEquals("Red Dragon", hotel.getName());
    }

    @Test
    public void getHotelByIdTestFalse() {
        List<Hotel> hotels = Arrays.asList(
                new Hotel(1, "Tourist", 3, "tourist.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(2, "Red Dragon", 3, "redragon.cn", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(3, "Little Italia", 3, "l-italia.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()));
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        when(hotelService.getHotelById(2)).thenReturn(hotels.get(1));
        Hotel hotel = hotelService.getHotelById(2);
        assertNotEquals("Tourist", hotel.getName());
    }

    @Test
    public void getAllHotelsTestTrue() {
        List<Hotel> hotels = Arrays.asList(
                new Hotel(1, "Tourist", 3, "tourist.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(2, "Red Dragon", 3, "redragon.cn", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(3, "Little Italia", 3, "l-italia.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()));
        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
        when(hotelServiceMock.getAllHotels()).thenReturn(hotels);
        List<Hotel> hotelList = hotelServiceMock.getAllHotels();
        assertEquals(3, hotelList.size());
    }

    @Test
    public void getAllHotelsTestFalse() throws SQLException {
        List<Hotel> hotels = Arrays.asList(
                new Hotel(1, "Tourist", 3, "tourist.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(2, "Red Dragon", 3, "redragon.cn", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()),
                new Hotel(3, "Little Italia", 3, "l-italia.by", BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516), new ArrayList<>()));
        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
        when(hotelServiceMock.getAllHotels()).thenReturn(hotels);
        List<Hotel> hotelList = hotelServiceMock.getAllHotels();
        assertNotEquals(0, hotelList.size());
    }
}
