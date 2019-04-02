package by.rekuts.travelagency.service.mocktests

import java.util

import by.rekuts.travelagency.domain.User
import by.rekuts.travelagency.repository.UserSpecification
import by.rekuts.travelagency.service.impl.UserServiceImpl
import org.junit.Assert.{assertEquals, assertNotEquals}
import org.junit.{Before, Test}
import org.mockito.Matchers.{any, anyInt}
import org.mockito.Mockito._

class UserServiceImplTest {
  private var users: util.List[User] = _

  @Before
  def initializeUsersList(): Unit = {
    users = util.List.of(
      new User(1, "superlogin1", "thisispassword"),
      new User(2, "superlogin2", "thisispassword"),
      new User(3, "superlogin3", "thisispassword"))
  }

  @Test
  def insertTest(): Unit = {
    val userService = mock(classOf[UserServiceImpl])
    doNothing.when(userService).insert(any(classOf[User]))
    userService.insert(new User)
    userService.insert(new User)
    verify(userService, times(2)).insert(any(classOf[User]))
  }

  @Test
  def deleteTest(): Unit = {
    val userService = mock(classOf[UserServiceImpl])
    doNothing.when(userService).insert(any(classOf[User]))
    userService.delete(6)
    userService.delete(8)
    verify(userService, times(2)).delete(anyInt)
  }

  @Test
  def getUserByIdTestTrue(): Unit = {
    val userService = mock(classOf[UserServiceImpl])
    val specification = new UserSpecification(2)
    val singletonList = util.Arrays.asList(users.get(1))
    when(userService.getList(specification)).thenReturn(singletonList)
    val user = userService.getList(specification).get(0)
    assertEquals("superlogin2", user.login)
  }

  @Test
  def getUserByIdTestFalse(): Unit = {
    val userService = mock(classOf[UserServiceImpl])
    val specification = new UserSpecification(2)
    val singletonList = util.Arrays.asList(users.get(1))
    when(userService.getList(specification)).thenReturn(singletonList)
    val user = userService.getList(specification).get(0)
    assertNotEquals("superlogin1", user.login)
  }

  @Test
  def getAllUsersTestTrue(): Unit = {
    val userService = mock(classOf[UserServiceImpl])
    val specification = new UserSpecification
    when(userService.getList(specification)).thenReturn(users)
    val tourList = userService.getList(specification)
    assertEquals(3, tourList.size)
  }

  @Test
  def getAllUsersTestFalse(): Unit = {
    val userService = mock(classOf[UserServiceImpl])
    val specification = new UserSpecification
    when(userService.getList(specification)).thenReturn(users)
    val tourList = userService.getList(specification)
    assertNotEquals(0, tourList.size)
  }
}
