package by.rekuts.travelagency.service.mocktests;

import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static List<User> users;

    @Before
    public void initializeUsersList() {
        users = List.of(
                new User(1, "superlogin1", "thisispassword"),
                new User(2, "superlogin2", "thisispassword"),
                new User(3, "superlogin3", "thisispassword"));
    }

    @Test
    public void insertTest(){
        var userService = mock(UserServiceImpl.class);
        doNothing().when(userService).insert(any(User.class));
        userService.insert(new User());
        userService.insert(new User());
        verify(userService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        var userService = mock(UserServiceImpl.class);
        doNothing().when(userService).insert(any(User.class));
        userService.delete(6);
        userService.delete(8);
        verify(userService, times(2)).delete(anyInt());
    }

    @Test
    public void getUserByIdTestTrue() {
        var userService = mock(UserServiceImpl.class);
        var specification = new UserSpecification(2);
        var singletonList = List.of(users.get(1));
        when(userService.getList(specification)).thenReturn(singletonList);
        var user = userService.getList(specification).get(0);
        assertEquals("superlogin2", user.getLogin());
    }

    @Test
    public void getUserByIdTestFalse() {
        var userService = mock(UserServiceImpl.class);
        var specification = new UserSpecification(2);
        var singletonList = List.of(users.get(1));
        when(userService.getList(specification)).thenReturn(singletonList);
        var user = userService.getList(specification).get(0);
        assertNotEquals("superlogin1", user.getLogin());
    }

    @Test
    public void getAllUsersTestTrue() {
        var userService = mock(UserServiceImpl.class);
        var specification = new UserSpecification();
        when(userService.getList(specification)).thenReturn(users);
        var tourList = userService.getList(specification);
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllUsersTestFalse() {
        var userService = mock(UserServiceImpl.class);
        var specification = new UserSpecification();
        when(userService.getList(specification)).thenReturn(users);
        var tourList = userService.getList(specification);
        assertNotEquals(0, tourList.size());
    }
}
