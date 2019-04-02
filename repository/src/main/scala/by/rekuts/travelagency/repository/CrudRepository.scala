package by.rekuts.travelagency.repository

import java.util

import javax.persistence.{EntityManager, PersistenceContext}

trait CrudRepository[T <: java.io.Serializable] {
  @PersistenceContext
  var entityManager: EntityManager = _
  /**
    * This method inserts object of T class to database
    *
    * @param t - object
    */
  def insert(t: T): Unit = entityManager.persist(t)

  /**
    * This method updates object of T class in database
    *
    * @param t - object
    */
  def update(t: T): Unit

  /**
    * This method deletes object with id <b>id</b> from database
    *
    * @param id - id of the object
    */
  def delete(id: Int): Unit

  /**
    * @return list of all objects from database or by id
    */
  def getList(specification: Specification[_]): util.List[T]
}
