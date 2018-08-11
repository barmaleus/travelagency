package by.rekuts.travelagency.logic.service.impl;

import by.rekuts.travelagency.logic.repository.CountryRepository;
import by.rekuts.travelagency.logic.repository.Specification;
import by.rekuts.travelagency.logic.domain.Country;
import by.rekuts.travelagency.logic.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	CountryRepository countryRepository;

	@Transactional
	@Override
	public void insert(Country country) {
		countryRepository.insert(country);
	}

	@Transactional
	@Override
	public void delete(int id) {
		countryRepository.delete(id);
	}

	@Transactional
	@Override
	public List<Country> getList(Specification specification) {
		return countryRepository.getList(specification);
	}
}
