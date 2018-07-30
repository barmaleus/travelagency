//package by.rekuts.travelagency.service;
//
//import by.rekuts.travelagency.dao.subjects.Hotel;
//import by.rekuts.travelagency.service.impl.HotelServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.*;
//
//public class HotelServiceImplTest {
//    private List<Hotel> hotels;
//
//    @Before
//    public void initializeHotelsList() {
////        Hotel hotel1 = Hotel.builder()
////                .hotelId(1)
////                .name("Tourist")
////                .stars(3)
////                .website("tourist.by")
////                .latitude(BigDecimal.valueOf(85.15465212))
////                .longitude(BigDecimal.valueOf(-48.85465516))
////                .features(new ArrayList<>())
////                .build();
////        Hotel hotel2 = Hotel.builder()
////                .hotelId(2)
////                .name("Red Dragon")
////                .stars(3)
////                .website("redragon.cn")
////                .latitude(BigDecimal.valueOf(85.15465212))
////                .longitude(BigDecimal.valueOf(-48.85465516))
////                .features(new ArrayList<>())
////                .build();
////        Hotel hotel3 = Hotel.builder()
////                .hotelId(3)
////                .name("Little Italia")
////                .stars(3)
////                .website("l-italia.by")
////                .latitude(BigDecimal.valueOf(85.15465212))
////                .longitude(BigDecimal.valueOf(-48.85465516))
////                .features(new ArrayList<>())
////                .build();
////        hotels = Arrays.asList(hotel1, hotel2, hotel3);
//    }
//
//    @Test
//    public void insertTest(){
//        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
//        doNothing().when(hotelService).insert(any(Hotel.class));
//        hotelService.insert(any(Hotel.class));
//        hotelService.insert(any(Hotel.class));
//        verify(hotelService, times(2)).insert(any());
//    }
//
//    @Test
//    public void deleteTest() {
//        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
//        doNothing().when(hotelService).insert(any(Hotel.class));
//        hotelService.delete(6);
//        hotelService.delete(8);
//        verify(hotelService, times(2)).delete(anyInt());
//    }
//
//    @Test
//    public void getHotelByIdTestTrue() {
//        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
//        when(hotelService.getHotelById(2)).thenReturn(hotels.get(1));
//        Hotel hotel = hotelService.getHotelById(2);
//        assertEquals("Red Dragon", hotel.getName());
//    }
//
//    @Test
//    public void getHotelByIdTestFalse() {
//        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
//        when(hotelService.getHotelById(2)).thenReturn(hotels.get(1));
//        Hotel hotel = hotelService.getHotelById(2);
//        assertNotEquals("Tourist", hotel.getName());
//    }
//
//    @Test
//    public void getAllHotelsTestTrue() {
//        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
//        when(hotelServiceMock.getAllHotels()).thenReturn(hotels);
//        List<Hotel> hotelList = hotelServiceMock.getAllHotels();
//        assertEquals(3, hotelList.size());
//    }
//
//    @Test
//    public void getAllHotelsTestFalse() {
//        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
//        when(hotelServiceMock.getAllHotels()).thenReturn(hotels);
//        List<Hotel> hotelList = hotelServiceMock.getAllHotels();
//        assertNotEquals(0, hotelList.size());
//    }
//}
