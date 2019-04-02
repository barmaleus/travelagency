package by.rekuts.travelagency.domain

import by.rekuts.travelagency.domain.UserRole.UserRole
import org.springframework.security.core.authority.AuthorityUtils

class CurrentUser(var user: User) extends org.springframework.security.core.userdetails.User(user.login, user.password, AuthorityUtils.createAuthorityList(user.role.toString)) {
  def getUser: User = user

  def getId: Int = user.id

  def getRole: UserRole = user.role

  override def toString: String = "CurrentUser{" + "user=" + user + "} " + super.toString

  override def equals(o: Any): Boolean = {
    o match {
      case u: CurrentUser => {
        user.equals(u.getUser)
        user.id.equals(u.getId)
        user.role.equals(u.getRole)
      }
      case _ => false
    }
  }

  override def hashCode(): Int = super.hashCode()
}
