package by.rekuts.travelagency.service;

import by.rekuts.travelagency.repository.HotelRepository;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.domain.Hotel;

import java.util.List;

/**
 * Interface HotelService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of HotelRepository interface and could call its methods.
 */
public interface HotelService {

    /**
     * This method calls {@link HotelRepository#insert(Hotel)}
     * It inserts object of Hotel class to database
     * @param hotel - object of the hotel
     * @see Hotel
     */
    void insert(Hotel hotel);

    /**
     * This method calls {@link HotelRepository#delete(int)}
     * It deletes hotel with id <b>id</b> from database
     * @param id - id of the hotel
     * @see Hotel#id
     */
    void delete(int id);

    /**
     * This method calls {@link HotelRepository#getList(Specification)}
     * @return list of all hotels from database or list with one hotel selected by id
     */
    List<Hotel> getList(Specification specification);
}
