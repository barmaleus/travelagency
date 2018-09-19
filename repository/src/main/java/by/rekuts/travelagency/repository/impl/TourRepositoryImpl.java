package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.repository.TourSpecification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TourRepositoryImpl implements TourRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Tour tour) {
        entityManager.persist(tour);
    }

    @Override
    public void update(Tour tour) {
        Tour dbTour = entityManager.find(Tour.class, tour.getId());
        dbTour.setPhoto(tour.getPhoto());
        dbTour.setDate(tour.getDate());
        dbTour.setDuration(tour.getDuration());
        dbTour.setDescription(tour.getDescription());
        dbTour.setCost(tour.getCost());
        dbTour.setTourType(tour.getTourType());
        dbTour.setHotel(tour.getHotel());
        dbTour.setCountry(tour.getCountry());
        dbTour.setUsers(tour.getUsers());
        entityManager.merge(dbTour);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Tour.class, id));
    }

    @Override
    public List<Tour> getList(Specification specification) {
        final TourSpecification tourSpecification = (TourSpecification) specification;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tour> criteriaQuery = builder.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        List<Predicate> predicates;
        predicates = tourSpecification.getPredicates(root, builder);
        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
