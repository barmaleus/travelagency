package by.rekuts.travelagency.logic.service;

import by.rekuts.travelagency.logic.config.TestRepositoryConfig;
import by.rekuts.travelagency.logic.domain.Hotel;
import by.rekuts.travelagency.logic.repository.HotelSpecification;
import by.rekuts.travelagency.logic.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class HotelServiceImplTest {
    private List<Hotel> hotels;

    @Before
    public void initializeHotelsList() {
        Hotel hotel1 = new Hotel();
        hotel1.setId(1);
        hotel1.setName("Tourist");
        hotel1.setStars(3);
        hotel1.setWebsite("tourist.by");
        hotel1.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel1.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel1.setFeatures(new ArrayList<>());
        Hotel hotel2 = new Hotel();
        hotel2.setId(2);
        hotel2.setName("Red Dragon");
        hotel2.setStars(3);
        hotel2.setWebsite("redragon.cn");
        hotel2.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel2.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel2.setFeatures(new ArrayList<>());
        Hotel hotel3 = new Hotel();
        hotel3.setId(3);
        hotel3.setName("Little Italia");
        hotel3.setStars(3);
        hotel3.setWebsite("l-italia.by");
        hotel3.setLatitude(BigDecimal.valueOf(85.15465212));
        hotel3.setLongitude(BigDecimal.valueOf(-48.85465516));
        hotel3.setFeatures(new ArrayList<>());
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
        HotelSpecification specification = new HotelSpecification(2);
        List<Hotel> singletonList = Collections.singletonList(hotels.get(1));
        when(hotelService.getList(specification)).thenReturn(singletonList);
        Hotel hotel = hotelService.getList(specification).get(0);
        assertEquals("Red Dragon", hotel.getName());
    }

    @Test
    public void getHotelByIdTestFalse() {
        HotelServiceImpl hotelService = mock(HotelServiceImpl.class);
        HotelSpecification specification = new HotelSpecification(2);
        List<Hotel> singletonList = Collections.singletonList(hotels.get(1));
        when(hotelService.getList(specification)).thenReturn(singletonList);
        Hotel hotel = hotelService.getList(specification).get(0);
        assertNotEquals("Tourist", hotel.getName());
    }

    @Test
    public void getAllHotelsTestTrue() {
        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
        HotelSpecification specification = new HotelSpecification();
        when(hotelServiceMock.getList(specification)).thenReturn(hotels);
        List<Hotel> hotelList = hotelServiceMock.getList(specification);
        assertEquals(3, hotelList.size());
    }

    @Test
    public void getAllHotelsTestFalse() {
        HotelServiceImpl hotelServiceMock = mock(HotelServiceImpl.class);
        HotelSpecification specification = new HotelSpecification();
        when(hotelServiceMock.getList(specification)).thenReturn(hotels);
        List<Hotel> hotelList = hotelServiceMock.getList(specification);
        assertNotEquals(0, hotelList.size());
    }
}
