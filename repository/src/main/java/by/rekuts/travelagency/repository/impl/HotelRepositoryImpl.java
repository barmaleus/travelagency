package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Hotel;
import by.rekuts.travelagency.repository.HotelRepository;
import by.rekuts.travelagency.repository.HotelSpecification;
import by.rekuts.travelagency.repository.Specification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
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
    public void update(Hotel hotel) {
        var dbHotel = entityManager.find(Hotel.class, hotel.getId());
        dbHotel.setName(hotel.getName());
        dbHotel.setStars(hotel.getStars());
        dbHotel.setWebsite(hotel.getWebsite());
        dbHotel.setLatitude(hotel.getLatitude());
        dbHotel.setLongitude(hotel.getLongitude());
        dbHotel.setFeatures(hotel.getFeatures());
        entityManager.merge(dbHotel);
    }

    @Override
    public void delete(int id) {
        var query = entityManager.createQuery(
                "SELECT t.id FROM Tour AS t WHERE hotel_id = ?1", Integer.class);
        query.setParameter(1, id);
        var results = query.getResultList();
        if (results == null || results.isEmpty()) {
            entityManager.remove(entityManager.find(Hotel.class, id));
            log.info("Hotel deleted");
        } else {
            log.info("Can't delete hotel. There are some foreign keys references on it.");
        }
    }

    @Override
    public List<Hotel> getList(Specification specification) {
        final var hotelSpecification = (HotelSpecification) specification;
        var builder = entityManager.getCriteriaBuilder();
        var criteriaQuery = builder.createQuery(Hotel.class);
        var hotelRoot = criteriaQuery.from(Hotel.class);
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
