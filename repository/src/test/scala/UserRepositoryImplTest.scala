import by.rekuts.travelagency.domain.User
import by.rekuts.travelagency.repository.config.TestRepositoryConfig
import by.rekuts.travelagency.repository.{UserRepository, UserSpecification}
import org.junit.runner.RunWith
import org.junit.{Assert, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}
import org.springframework.transaction.annotation.Transactional

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes = Array(classOf[TestRepositoryConfig]))
@ActiveProfiles(Array("testScope"))
@Transactional
class UserRepositoryImplTest {
  @Autowired
  private var userRepository: UserRepository = _

  @Test
  def insertTestTrue(): Unit = {
    val user = new User
    user.login_$eq("Userok")
    user.password_$eq("veryDifficultPassword")
    val countFirst = userRepository.getList(new UserSpecification).size
    userRepository.insert(user)
    val countLast = userRepository.getList(new UserSpecification).size
    Assert.assertEquals(1, countLast - countFirst)
  }

  @Test
  def deleteUserTestTrue(): Unit = {
    val countFirst = userRepository.getList(new UserSpecification).size
    userRepository.delete(1)
    val countLast = userRepository.getList(new UserSpecification).size
    Assert.assertEquals(1, countFirst - countLast)
  }

  @Test
  def getUserByIdTest(): Unit = {
    Assert.assertEquals("brownbutterfly205", userRepository.getList(new UserSpecification(2)).get(0).login.trim)
  }

  @Test
  def getUserByIdWithToursTest(): Unit = {
    val user = userRepository.getList(new UserSpecification(101)).get(0)
    Assert.assertEquals(2, user.tours.size)
  }

  @Test
  def getAllUsersTest(): Unit = {
    val users = userRepository.getList(new UserSpecification)
    Assert.assertEquals(103, users.size)
  }
}
