package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.repository.HotelRepository;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.domain.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
@Repository
public class HotelRepositoryImpl implements HotelRepository {
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
    public List<Hotel> getList(Specification specification) {
        final HotelSpecification hotelSpecification = (HotelSpecification) specification;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hotel> criteriaQuery = builder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = criteriaQuery.from(Hotel.class);
        List<Predicate> predicates;
        predicates = hotelSpecification.getPredicates(hotelRoot, builder);

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
