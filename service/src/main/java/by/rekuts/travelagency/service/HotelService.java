package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.service.impl.CrudService;

/**
 * Interface HotelService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of HotelRepository interface and could call its methods.
 */
public interface HotelService extends CrudService<Hotel> {
}
