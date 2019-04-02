package by.rekuts.travelagency.domain

import javax.persistence._
import javax.validation.constraints.NotNull
import org.hibernate.annotations.{OptimisticLockType, OptimisticLocking}


@OptimisticLocking(`type` = OptimisticLockType.VERSION)
@Entity
@Table
@NamedQueries(Array(new NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")))
class Country extends Serializable {
  @Id
  @SequenceGenerator(name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
  var id: Int = _

  @NotNull
  @Column(nullable = false)
  var name: String = _

  @Version
  var version: Int = _

}
