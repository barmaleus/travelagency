package by.rekuts.travelagency.service;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class UserServiceImplTest {
    private static List<User> users;

    @Before
    public void initializeUsersList() {
        users = Arrays.asList(
                new User(1, "superlogin1", "thisispassword"),
                new User(2, "superlogin2", "thisispassword"),
                new User(3, "superlogin3", "thisispassword"));
    }


    @Test
    public void insertTest(){
        UserServiceImpl userService = mock(UserServiceImpl.class);
        doNothing().when(userService).insert(any(User.class));
        userService.insert(new User());
        userService.insert(new User());
        verify(userService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        doNothing().when(userService).insert(any(User.class));
        userService.delete(6);
        userService.delete(8);
        verify(userService, times(2)).delete(anyInt());
    }

    @Test
    public void getReviewByIdTestTrue() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getUserById(2)).thenReturn(users.get(1));
        User user = userService.getUserById(2);
        assertEquals("superlogin2", user.getLogin());
    }

    @Test
    public void getReviewByIdTestFalse() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getUserById(2)).thenReturn(users.get(1));
        User user = userService.getUserById(2);
        assertNotEquals("superlogin1", user.getLogin());
    }

    @Test
    public void getAllReviewsTestTrue() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getAllUsers()).thenReturn(users);
        List<User> tourList = userService.getAllUsers();
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllReviewsTestFalse() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getAllUsers()).thenReturn(users);
        List<User> tourList = userService.getAllUsers();
        assertNotEquals(0, tourList.size());
    }
}
