package by.rekuts.travelagency.domain

import java.io.Serializable
import java.util

import by.rekuts.travelagency.domain.UserRole.UserRole
import javax.validation.constraints.NotNull
import javax.persistence._
import org.hibernate.annotations.{OptimisticLockType, OptimisticLocking, Type, TypeDef}

@OptimisticLocking(`type` = OptimisticLockType.VERSION)
@Entity
@Table(name = "\"user\"")
@TypeDef(name = "role_enum", typeClass = classOf[RoleEnumType])
class User() extends Serializable {

  def this(id: Int, login: String, password: String) {
    this()
    this.id = id
    this.login = login
    this.password = password
  }

  @Id
  @SequenceGenerator(name = "jpaSequence", sequenceName = "gpa_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
  var id: Int = _

  @NotNull
  @Column(nullable = false, unique = true)
  var login: String = _

  var password: String = _

  @Type(`type` = "role_enum")
  @Enumerated(EnumType.STRING)
  var role: UserRole = UserRole.ROLE_MEMBER

  @ManyToMany(cascade = Array(CascadeType.ALL))
  @JoinTable(
    name = "user_tour",
    joinColumns = Array(new JoinColumn(name = "user_id")),
    inverseJoinColumns = Array(new JoinColumn(name = "tour_id"))
  )
  var tours: java.util.List[Tour] = new util.ArrayList[Tour]()

  @Version
  var version: Integer = _
}

object UserRole extends Enumeration {
  type UserRole = Value
  val ROLE_MEMBER, ROLE_ADMIN = Value
}
