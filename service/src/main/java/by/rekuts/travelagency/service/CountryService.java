package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.service.impl.CrudService;

/**
 * Interface CountryService concerns to Service layer.
 * Realisations of this interface has access to DAO layer
 * in the face of CountryRepository interface and could call its methods.
 */
public interface CountryService extends CrudService<Country> {
}
