package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.aspects.LogReturn;
import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.domain.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {
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
    public Country getCountryById(int id) {
        return entityManager.find(Country.class, id);
    }

    @Override
	public List<Country> getAllCountries() {
		TypedQuery<Country> query =
				entityManager.createNamedQuery("Country.findAll", Country.class);
		return query.getResultList();
	}
}
