package by.rekuts.travelagency.service

import java.util

import by.rekuts.travelagency.repository.Specification

trait CrudService[T <: java.io.Serializable] {
  /**
    * This method calls repository method 'insert' of T-object
    * It inserts object of the T class to database
    *
    * @param t - inserted object
    */
  def insert(t: T): Unit

  /**
    * This method calls repository method 'update' of T-object
    * It updates object of the T class in database
    *
    * @param t - updated object
    */
  def update(t: T): Unit

  /**
    * This method calls repository method 'delete' of T-object
    * It deletes object with id <b>id</b> from database
    *
    * @param id - id of the object
    */
  def delete(id: Int): Unit

  /**
    * This method calls repository method 'getList(Specification)'
    *
    * @return list of all objects from database or list with one object selected by id
    */
  def getList(specification: Specification[_]): util.List[T]
}
