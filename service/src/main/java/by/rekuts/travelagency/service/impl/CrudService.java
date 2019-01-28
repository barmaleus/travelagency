package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.repository.Specification;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends Serializable> {
    /**
     * This method calls repository method 'insert' of T-object
     * It inserts object of the T class to database
     * @param t - inserted object
     */
    void insert(T t);

    /**
     * This method calls repository method 'update' of T-object
     * It updates object of the T class in database
     * @param t - updated object
     */
    void update(T t);

    /**
     * This method calls repository method 'delete' of T-object
     * It deletes object with id <b>id</b> from database
     * @param id - id of the object
     */
    void delete(int id);

    /**
     * This method calls repository method 'getList(Specification)'
     * @return list of all objects from database or list with one object selected by id
     */
    List<T> getList(Specification specification);
}
