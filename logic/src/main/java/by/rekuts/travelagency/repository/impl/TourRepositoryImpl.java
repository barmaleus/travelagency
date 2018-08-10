package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.TourRepository;
import by.rekuts.travelagency.repository.TourSpecification;
import by.rekuts.travelagency.domain.Tour;
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
    public void delete(int id) {
        entityManager.remove(entityManager.find(Tour.class, id));
    }

    @LogReturn
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
