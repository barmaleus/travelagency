package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.Hotel;
import by.rekuts.travelagency.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class HotelServiceImplTest {
    private List<Hotel> hotels;

    @Before
    public void initializeHotelsList() {
        Hotel hotel1 = new Hotel.HotelBuilder(1, "Tourist")
                .withOptionalStars(3)
                .withOptionalWebsite("tourist.by")
                .withOptionalLocation(BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516))
                .withOptionalFeatures(new ArrayList<>())
                .buildHotel();
        Hotel hotel2 = new Hotel.HotelBuilder(2, "Red Dragon")
                .withOptionalStars(3)
                .withOptionalWebsite("redragon.cn")
                .withOptionalLocation(BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516))
                .withOptionalFeatures(new ArrayList<>())
                .buildHotel();
        Hotel hotel3 = new Hotel.HotelBuilder(3, "Little Italia")
                .withOptionalStars(3)
                .withOptionalWebsite("l-italia.by")
                .withOptionalLocation(BigDecimal.valueOf(85.15465212), BigDecimal.valueOf(-48.85465516))
                .withOptionalFeatures(new ArrayList<>())
                .buildHotel();
        hotels = Arrays.asList(hotel1, hotel2, hotel3);
    }

    @Test
    public void insertTest(){
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        doNothing().when(hotelService).insert(any(Hotel.class));
        hotelService.insert(any(Hotel.class));
        hotelService.insert(any(Hotel.class));
        verify(hotelService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        doNothing().when(hotelService).insert(any(Hotel.class));
        hotelService.delete(6);
        hotelService.delete(8);
        verify(hotelService, times(2)).delete(anyInt());
    }

    @Test
    public void getHotelByIdTestTrue() {
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        when(hotelService.getHotelById(2)).thenReturn(hotels.get(1));
        Hotel hotel = hotelService.getHotelById(2);
        assertEquals("Red Dragon", hotel.getName());
    }

    @Test
    public void getHotelByIdTestFalse() {
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        when(hotelService.getHotelById(2)).thenReturn(hotels.get(1));
        Hotel hotel = hotelService.getHotelById(2);
        assertNotEquals("Tourist", hotel.getName());
    }

    @Test
    public void getAllHotelsTestTrue() {
        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
        when(hotelServiceMock.getAllHotels()).thenReturn(hotels);
        List<Hotel> hotelList = hotelServiceMock.getAllHotels();
        assertEquals(3, hotelList.size());
    }

    @Test
    public void getAllHotelsTestFalse() {
        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
        when(hotelServiceMock.getAllHotels()).thenReturn(hotels);
        List<Hotel> hotelList = hotelServiceMock.getAllHotels();
        assertNotEquals(0, hotelList.size());
    }
}
