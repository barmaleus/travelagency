package by.rekuts.travelagency.service.integration;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    private User user = new User();

    @Before
    public void initializeUsersList() {
        user.setLogin("superuser1");
        user.setPassword("superpassword1");
    }

    @Test
    public void insertUserTest(){
        int countUsersFirst = userService.getAllUsers().size();
        userService.insert(user);
        int countUsersLast = userService.getAllUsers().size();
        assertEquals(1, countUsersLast - countUsersFirst);
    }

    @Test (expected = PersistenceException.class)
    public void insertNullUserTest(){
        userService.insert(new User());
    }

    @Test
    public void deleteTest() {
        int countUsersFirst = userService.getAllUsers().size();
        userService.delete(1);
        int countUsersLast = userService.getAllUsers().size();
        assertEquals(1, countUsersFirst - countUsersLast);
    }

    @Test
    public void getReviewByIdTestTrue() {
        User user = userService.getUserById(1);
        assertEquals("bluegoose681", user.getLogin());
    }

    @Test
    public void getReviewByIdTestFalse() {
        User user = userService.getUserById(102);
        assertEquals(null, user);
    }

    @Test
    public void getAllReviewsTestTrue() {
        List<User> tourList = userService.getAllUsers();
        assertEquals(101, tourList.size());
    }
}
