package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.dao.HotelDao;
import by.rekuts.travelagency.domain.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
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
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT t.id FROM Tour AS t WHERE hotel_id = ?1", Integer.class);
        query.setParameter(1, id);
        List<Integer> results = query.getResultList();
        if (results == null || results.isEmpty()) {
            entityManager.remove(entityManager.find(Hotel.class, id));
            log.info("Hotel deleted");
        } else {
            log.info("Can't delete hotel. There are some foreign keys references on it.");
        }
    }

    @LogReturn
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
