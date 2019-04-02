package by.rekuts.travelagency.service.impl

import java.util

import by.rekuts.travelagency.domain.Tour
import by.rekuts.travelagency.repository.{CSVTourLoader, Specification, TourRepository}
import by.rekuts.travelagency.service.TourService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TourServiceImpl extends TourService {
  @Autowired
  private var tourRepository: TourRepository = _
  @Autowired
  private var csvTourLoader: CSVTourLoader = _

  @Transactional
  override def insert(tour: Tour): Unit = tourRepository.insert(tour)

  @Transactional
  override def update(tour: Tour): Unit = tourRepository.update(tour)

  @Transactional
  override def delete(id: Int): Unit = tourRepository.delete(id)

  @Transactional
  override def getList(specification: Specification[_]): util.List[Tour] = tourRepository.getList(specification)

  @Transactional
  override def importCsvTours(csvFileContent: String): Unit = csvTourLoader.importCsvTours(csvFileContent)
}
