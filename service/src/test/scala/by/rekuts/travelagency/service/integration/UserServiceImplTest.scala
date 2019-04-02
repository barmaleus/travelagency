package by.rekuts.travelagency.service.integration

import by.rekuts.travelagency.domain.User
import by.rekuts.travelagency.repository.UserSpecification
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.service.UserService
import javax.persistence.PersistenceException
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class UserServiceImplTest {
  @Autowired
  private var userService: UserService = _

  private val user = new User

  @Before
  def initializeUsersList(): Unit = {
    user.login_$eq("superuser1")
    user.password_$eq("superpassword1")
  }

  @Test
  def insertUserTest(): Unit = {
    val countUsersFirst = userService.getList(new UserSpecification).size
    userService.insert(user)
    val countUsersLast = userService.getList(new UserSpecification).size
    assertEquals(1, countUsersLast - countUsersFirst)
  }

  @Test(expected = classOf[PersistenceException])
  def insertNullUserTest(): Unit = userService.insert(new User)

  @Test
  def deleteTest(): Unit = {
    val countUsersFirst = userService.getList(new UserSpecification).size
    userService.delete(1)
    val countUsersLast = userService.getList(new UserSpecification).size
    assertEquals(1, countUsersFirst - countUsersLast)
  }

  @Test
  def obtainUserByIdTestTrue(): Unit = {
    val user = userService.getList(new UserSpecification(1)).get(0)
    assertEquals("bluegoose681", user.login)
  }

  @Test(expected = classOf[IndexOutOfBoundsException])
  def obtainUserByIdTestFalse1(): Unit = {
    userService.getList(new UserSpecification(102)).get(0)
  }

  @Test
  def obtainUserByIdTestFalse2(): Unit = {
    val users = userService.getList(new UserSpecification(102))
    assertTrue(users.isEmpty)
  }

  @Test
  def obtainAllUsersTestTrue(): Unit = {
    val tourList = userService.getList(new UserSpecification)
    assertEquals(103, tourList.size)
  }
}
