package by.rekuts.travelagency.service.impl

import java.util

import by.rekuts.travelagency.domain.Hotel
import by.rekuts.travelagency.repository.{HotelRepository, Specification}
import by.rekuts.travelagency.service.HotelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HotelServiceImpl extends HotelService {
  @Autowired
  private var hotelRepository: HotelRepository = _

  @Transactional
  override def insert(hotel: Hotel): Unit = hotelRepository.insert(hotel)

  @Transactional
  override def update(hotel: Hotel): Unit = hotelRepository.update(hotel)

  @Transactional
  override def delete(id: Int): Unit = hotelRepository.delete(id)

  @Transactional
  override def getList(specification: Specification[_]): util.List[Hotel] = hotelRepository.getList(specification)
}
