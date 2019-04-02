package by.rekuts.travelagency.repository

import by.rekuts.travelagency.domain.Country

class CountrySpecification extends Specification[Country] {
  def this(id: Int) {
    this()
    this.id = id
  }
}
