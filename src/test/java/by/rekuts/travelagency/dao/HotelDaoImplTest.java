package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.dao.subjects.Hotel;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
public class HotelDaoImplTest {

    @Autowired
    HotelDao hotelDao;

    @Test
    public void insertTest() {

    }

    @Test
    public void deleteTestTrue() {

    }

    @Test
    public void getHotelByIdTestTrue() {

    }

    @Test
    @Ignore
    public void getAllHotelsTestTrue() {
        List<Hotel> hotels = hotelDao.getAllHotels();
        Assert.assertEquals(198, hotels.size());
    }
}

