//package by.rekuts.travelagency.logic.service.integration;
//
//import by.rekuts.travelagency.domain.config.TestRepositoryConfig;
//import by.rekuts.travelagency.domain.User;
//import by.rekuts.travelagency.repository.UserSpecification;
//import by.rekuts.travelagency.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.PersistenceException;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class UserServiceImplTest {
//    @Autowired
//    UserService userService;
//
//    private User user = new User();
//
//    @Before
//    public void initializeUsersList() {
//        user.setLogin("superuser1");
//        user.setPassword("superpassword1");
//    }
//
//    @Test
//    public void insertUserTest(){
//        int countUsersFirst = userService.getList(new UserSpecification()).size();
//        userService.insert(user);
//        int countUsersLast = userService.getList(new UserSpecification()).size();
//        assertEquals(1, countUsersLast - countUsersFirst);
//    }
//
//    @Test (expected = PersistenceException.class)
//    public void insertNullUserTest(){
//        userService.insert(new User());
//    }
//
//    @Test
//    public void deleteTest() {
//        int countUsersFirst = userService.getList(new UserSpecification()).size();
//        userService.delete(1);
//        int countUsersLast = userService.getList(new UserSpecification()).size();
//        assertEquals(1, countUsersFirst - countUsersLast);
//    }
//
//    @Test
//    public void getUserByIdTestTrue() {
//        User user = userService.getList(new UserSpecification(1)).get(0);
//        assertEquals("bluegoose681", user.getLogin());
//    }
//
//    @Test (expected = IndexOutOfBoundsException.class)
//    public void getUserByIdTestFalse1() {
//        userService.getList(new UserSpecification(102)).get(0);
//    }
//
//    @Test
//    public void getUserByIdTestFalse2() {
//        List<User> users = userService.getList(new UserSpecification(102));
//        assertTrue(users.isEmpty());
//    }
//
//    @Test
//    public void getAllUsersTestTrue() {
//        List<User> tourList = userService.getList(new UserSpecification());
//        assertEquals(101, tourList.size());
//    }
//}
