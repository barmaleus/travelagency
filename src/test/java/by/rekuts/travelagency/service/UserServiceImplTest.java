package by.rekuts.travelagency.service;

import by.rekuts.travelagency.config.TestRepositoryConfig;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
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
    public void getUserByIdTestTrue() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        UserSpecification specification = new UserSpecification(2);
        List<User> singletonList = Collections.singletonList(users.get(1));
        when(userService.getList(specification)).thenReturn(singletonList);
        User user = userService.getList(specification).get(0);
        assertEquals("superlogin2", user.getLogin());
    }

    @Test
    public void getUserByIdTestFalse() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        UserSpecification specification = new UserSpecification(2);
        List<User> singletonList = Collections.singletonList(users.get(1));
        when(userService.getList(specification)).thenReturn(singletonList);
        User user = userService.getList(specification).get(0);
        assertNotEquals("superlogin1", user.getLogin());
    }

    @Test
    public void getAllUsersTestTrue() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        UserSpecification specification = new UserSpecification();
        when(userService.getList(specification)).thenReturn(users);
        List<User> tourList = userService.getList(specification);
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllUsersTestFalse() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        UserSpecification specification = new UserSpecification();
        when(userService.getList(specification)).thenReturn(users);
        List<User> tourList = userService.getList(specification);
        assertNotEquals(0, tourList.size());
    }
}
