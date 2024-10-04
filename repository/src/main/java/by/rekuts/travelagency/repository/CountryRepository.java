package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface CountryRepository concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with Country objects in database.
 */
public interface CountryRepository extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country> {
}
