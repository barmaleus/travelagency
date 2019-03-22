package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.repository.CountryRepository;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.repository.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class CountryRepositoryImpl implements CountryRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insert(Country country) {
		entityManager.persist(country);
	}

	@Override
	public void update(Country country) {
		var dbCountry = entityManager.find(Country.class, country.getId());
		dbCountry.setName(country.getName());
		entityManager.merge(dbCountry);
	}

	@Override
	public void delete(int id) {
	    entityManager.remove(entityManager.find(Country.class, id));
	}

    @Override
	public List<Country> getList(Specification specification) {
		final var countrySpecification = (CountrySpecification) specification;

		var builder = entityManager.getCriteriaBuilder();
		var criteriaQuery = builder.createQuery(Country.class);
		var countryRoot = criteriaQuery.from(Country.class);
        var predicates = countrySpecification.getPredicates(countryRoot, builder);

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
