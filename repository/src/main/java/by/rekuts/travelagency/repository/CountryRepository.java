package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Country;

/**
 * Interface CountryRepository concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Country objects in database.
 */
public interface CountryRepository extends CrudRepository<Country>{
}
