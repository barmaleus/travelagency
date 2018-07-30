package by.rekuts.travelagency.dao.impl;

import by.rekuts.travelagency.dao.CountryDao;
import by.rekuts.travelagency.dao.subjects.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void insert(Country country) {
		entityManager.persist(country);
	}

	@Override
	public void delete(int id) {
	    entityManager.remove(entityManager.find(Country.class, id));    //todo to test
	}

    @Override
    public Country getCountryById(int id) {
        return entityManager.find(Country.class, id);
    }

    @Override
	public List<Country> getAllCountries() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Country> criteriaQuery = builder.createQuery(Country.class);
		Root<Country> root = criteriaQuery.from(Country.class);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
}
