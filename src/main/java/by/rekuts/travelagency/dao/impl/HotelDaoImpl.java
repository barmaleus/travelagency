package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.dao.subjects.Hotel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Hotel.class, id));    //todo to test
    }

    @Override
    public Hotel getHotelById(int id) {
        return entityManager.find(Hotel.class, id);
    }

    @Override
    public List<Hotel> getAllHotels() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = builder.createQuery(Hotel.class);
        Root<Hotel> root = criteriaQuery.from(Hotel.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
