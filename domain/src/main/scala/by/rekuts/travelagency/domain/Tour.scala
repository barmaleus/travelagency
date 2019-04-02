package by.rekuts.travelagency.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.util

import by.rekuts.travelagency.domain.TourType.TourType
import javax.persistence._
import javax.validation.constraints.{NotNull, PositiveOrZero}
import org.hibernate.annotations.{OptimisticLockType, OptimisticLocking, Type, TypeDef}

@OptimisticLocking(`type` = OptimisticLockType.VERSION)
@Entity
@Table
@TypeDef(name = "tour_enum", typeClass = classOf[TourEnumType])
class Tour extends Serializable {
  @Id
  @SequenceGenerator(name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
  var id: Int = _

  var photo: String = _

  var date: LocalDate = _

  var duration: Int = _

  var description: String = _

  @PositiveOrZero
  var cost: BigDecimal = _

  @Column(name = "tour_type")
  @Type(`type` = "tour_enum")
  @Enumerated(EnumType.STRING)
  var tourType: TourType = _

  @NotNull
  @ManyToOne
  @JoinColumn(name = "hotel_id", nullable = false)
  var hotel: Hotel = _

  @NotNull
  @ManyToOne
  @JoinColumn(name = "country_id", nullable = false)
  var country: Country = _

  @ManyToMany(mappedBy = "tours", cascade = Array(CascadeType.ALL))
  var users: util.List[User] = new util.ArrayList[User]()

  @Version
  var version: Int = _
}

/**
  * Inner to class Tour enum TourType. It stores the names of tour types in the values.
  */
object TourType extends Enumeration {
  type TourType = Value
  val safari, rural, mountain, ski, ecotourism, health, cruise, education, adventure, cultural = Value
}
