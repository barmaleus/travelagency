package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.domain.Hotel;

import java.util.List;

/**
 * Interface HotelService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of HotelDao interface and could call its methods.
 */
public interface HotelService {

    /**
     * This method calls {@link HotelDao#insert(Hotel)}
     * It inserts object of Hotel class to database
     * @param hotel - object of the hotel
     * @see Hotel
     */
    void insert(Hotel hotel);

    /**
     * This method calls {@link HotelDao#delete(int)}
     * It deletes hotel with id <b>id</b> from database
     * @param id - id of the hotel
     * @see Hotel#hotelId
     */
    void delete(int id);

    /**
     * This method calls {@link HotelDao#getHotelById(int)}
     * @param id - id of the hotel
     * @return object of the hotel with id <b>id</b> from database if it exist
     */
    Hotel getHotelById(int id);

    /**
     * This method calls {@link HotelDao#getAllHotels()}
     * @return list of all hotels from database
     */
    List<Hotel> getAllHotels();
}
