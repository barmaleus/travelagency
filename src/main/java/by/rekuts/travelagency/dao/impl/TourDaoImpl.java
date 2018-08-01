package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.TourDao;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.domain.Tour;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        entityManager.remove(entityManager.find(Tour.class, id));    //todo to test
    }

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

    @Override
    public List<Tour> getToursByCriteria(Country country, LocalDate date, Integer duration, Tour.TourType tourType,
                                         BigDecimal minCost, BigDecimal maxCost, Integer stars){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> tourRoot = criteriaQuery.from(Tour.class);
//        Root<Country> countryRoot = criteriaQuery.from(Country.class);
//        criteriaQuery.multiselect(tourRoot, countryRoot);
//        criteriaQuery.select(tourRoot);
        List<Predicate> predicates = new ArrayList<>();
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
//        if(tourType != null) {
//            predicates.add(
//                    builder.equal(tourRoot.get("tour_type"), tourType)    //todo smth wrong
//            );
//        }
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
//        if (stars != null) {
//            predicates.add(
//                    builder.equal(tourRoot.get("stars"), stars)
//            );
//        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
