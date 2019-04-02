package by.rekuts.travelagency.service.impl

import java.util

import by.rekuts.travelagency.domain.Country
import by.rekuts.travelagency.repository.{CountryRepository, Specification}
import by.rekuts.travelagency.service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CountryServiceImpl extends CountryService {
  @Autowired
  private var countryRepository: CountryRepository = _

  @Transactional
  override def insert(country: Country): Unit = countryRepository.insert(country)

  @Transactional
  override def update(country: Country): Unit = countryRepository.update(country)

  @Transactional
  override def delete(id: Int): Unit = countryRepository.delete(id)

  @Transactional
  override def getList(specification: Specification[_]): util.List[Country] = countryRepository.getList(specification)
}
