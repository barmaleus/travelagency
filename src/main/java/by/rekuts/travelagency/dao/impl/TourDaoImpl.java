package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.domain.Tour;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TourDaoImpl implements TourDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Tour tour) {
        entityManager.persist(tour);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Tour.class, id));
    }

    @LogReturn
    @Override
    public Tour getTourById(int id) {
        return entityManager.find(Tour.class, id);
    }

    @Override
    public List<Tour> getAllTours() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @LogReturn
    @Override
    public List<Tour> getToursByCriteria(Country country, LocalDate date, Integer duration, Tour.TourType tourType,
                                         BigDecimal minCost, BigDecimal maxCost, Integer stars){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> tourRoot = criteriaQuery.from(Tour.class);
        Join<Tour, Country> countryJoin = tourRoot.join("country");
        Join<Tour, Hotel> hotelJoin = tourRoot.join("hotel");

        List<Predicate> predicates = new ArrayList<>();
        if (country != null) {
            predicates.add(
                    builder.equal(countryJoin.get("id"), country.getId())
            );
        }
        if (date != null) {
            predicates.add(
                    builder.equal(tourRoot.get("date"), date)
            );
        }
        if (duration != null) {
            predicates.add(
                    builder.equal(tourRoot.get("duration"), duration)
            );
        }
        if(tourType != null) {
            predicates.add(
                    builder.equal(tourRoot.get("tourType"), tourType)
            );
        }
        if (minCost != null) {
            predicates.add(
                    builder.ge(tourRoot.get("cost"), minCost)
            );
        }
        if (maxCost != null) {
            predicates.add(
                    builder.le(tourRoot.get("cost"), maxCost)
            );
        }
        if (stars != null) {
            predicates.add(
                    builder.equal(hotelJoin.get("stars"), stars)
            );
        }
        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @LogReturn
    @Override
    public List<Tour> getToursByUserId(int userId) {
        Query nativeQuery = entityManager.createNativeQuery("SELECT ut.tour_id FROM user_tour ut WHERE ut.user_id = ?");
        nativeQuery.setParameter(1, userId);
        List<Integer> list = nativeQuery.getResultList();
        List<Tour> tours = new ArrayList<>();
        for(Integer i : list) {
            TypedQuery<Tour> typedQuery =
                    entityManager.createQuery("SELECT t FROM Tour t WHERE t.id = ?1", Tour.class);
            typedQuery.setParameter(1, i);
            tours.add(typedQuery.getSingleResult());
        }
        return tours;
    }
}
