package by.rekuts.travelagency.domain

import java.math.BigDecimal

import javax.persistence._
import javax.validation.constraints.{Max, Min, NotNull}
import org.hibernate.annotations.{OptimisticLockType, OptimisticLocking, Type, TypeDef}

@OptimisticLocking(`type` = OptimisticLockType.VERSION)
@Entity
@Table
@TypeDef(name = "features", typeClass = classOf[HotelEnumArrayType])
class Hotel extends Serializable {
  @Id
  @SequenceGenerator(name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
  var id: Int = _

  @NotNull
  @Column(nullable = false)
  var name: String = _

  @Min(1) @Max(5)
  var stars: Int = _

  var website: String = _

  @Min(-90) @Max(90)
  var latitude: BigDecimal = _

  @Min(-180) @Max(180)
  var longitude: BigDecimal = _

  @Type(`type` = "features")
  var features: List[String] = _

  @Version
  var version: Integer =_

}

object Features extends Enumeration {
  type Features = Value
  val A = Value("swimming pool")
  val B = Value("all inclusive")
  val C = Value("fresh towel")
  val D = Value("carpets on the walls")
  val E = Value("free wifi")
  val F = Value("close to sea")
  val G = Value("free animators")
  val H = Value("free beer")
  val I = Value("air conditioning")
  val J = Value("children room")
}
