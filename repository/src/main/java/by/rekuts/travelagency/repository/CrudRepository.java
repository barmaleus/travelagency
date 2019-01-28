package by.rekuts.travelagency.repository;

import java.io.Serializable;
import java.util.List;

public interface CrudRepository<T extends Serializable> {
    /**
     * This method inserts object of T class to database
     * @param t - object
     */
    void insert(T t);

    /**
     * This method updates object of T class in database
     * @param t - object
     */
    void update(T t);

    /**
     * This method deletes object with id <b>id</b> from database
     * @param id - id of the object
     */
    void delete(int id);

    /**
     * @return list of all objects from database or by id
     */
    List<T> getList(Specification specification);
}
