package by.rekuts.travelagency.domain

import java.time.LocalDateTime
import javax.validation.constraints.NotNull
import javax.persistence._
import org.hibernate.annotations.{OptimisticLockType, OptimisticLocking}

@OptimisticLocking(`type` = OptimisticLockType.VERSION)
@Entity
@Table
class Review extends Serializable {
  @Id
  @SequenceGenerator(name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
  var id: Int = _

  @NotNull
  @Column(nullable = false)
  var date: LocalDateTime = _

  @NotNull
  @Column(nullable = false)
  var text: String = _

  @NotNull
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  var user: User = _

  @NotNull
  @ManyToOne
  @JoinColumn(name = "tour_id", nullable = false)
  var tour: Tour = _

  @Version
  var version: Int = _
}
