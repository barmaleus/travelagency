package by.rekuts.travelagency.service.impl

import java.util
import java.util.Collections

import by.rekuts.travelagency.domain.{Tour, User}
import by.rekuts.travelagency.repository.{Specification, UserRepository, UserSpecification}
import by.rekuts.travelagency.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService, UsernameNotFoundException}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl extends UserService with UserDetailsService {
  @Autowired
  private var userRepository: UserRepository = _

  @Transactional
  override def insert(user: User): Unit = userRepository.insert(user)

  @Transactional
  override def update(user: User): Unit = userRepository.update(user)

  @Transactional
  override def delete(id: Int): Unit = userRepository.delete(id)

  @Transactional
  override def getList(specification: Specification[_]): util.List[User] = userRepository.getList(specification)

  @Transactional(readOnly = true)
  override def loadUserByUsername(login: String): UserDetails = {
    val specification: UserSpecification = new UserSpecification
    specification.login_$eq(login)
    val user: User = userRepository.getList(specification).get(0)
    if (user != null) return new org.springframework.security.core.userdetails.User(
      user.login,
      user.password,
      Collections.singletonList(new SimpleGrantedAuthority(user.role.toString))
    )
    throw new UsernameNotFoundException("User '" + login + "' not found")
  }

  override def addTourToFavorites(user: User, tour: Tour): Unit = userRepository.addTourToFavorites(user, tour)

  override def removeTourFromFavorites(user: User, tour: Tour): Unit = userRepository.removeTourFromFavorites(user, tour)
}
