package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.Hotel

class HotelSpecification extends Specification[Hotel] {
  def this(id: Int) {
    this()
    this.id = id
  }
}
