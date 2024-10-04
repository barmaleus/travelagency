package by.rekuts.travelagency.repository;

import by.rekuts.travelagency.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Interface UserService concerns to DAO layer.
 * Realisations of this interface do some CRUD operations with User objects in database.
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
