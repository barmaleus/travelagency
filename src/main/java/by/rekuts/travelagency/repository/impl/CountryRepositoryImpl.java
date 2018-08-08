package by.rekuts.travelagency.repository.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.CountrySpecification;
import by.rekuts.travelagency.domain.Country;
import by.rekuts.travelagency.repository.CountryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
	public void delete(int id) {
	    entityManager.remove(entityManager.find(Country.class, id));
	}

    @LogReturn
    @Override
	public List<Country> getList(Specification specification) {
		final CountrySpecification countrySpecification = (CountrySpecification) specification;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
		Root<Country> countryRoot = criteriaQuery.from(Country.class);
        List<Predicate> predicates;
        predicates = countrySpecification.getPredicates(countryRoot, builder);

        if (!predicates.isEmpty()) {
            criteriaQuery.where(
                    predicates.toArray(new Predicate[]{})
            );
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
