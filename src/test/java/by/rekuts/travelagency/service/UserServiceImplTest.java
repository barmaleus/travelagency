package by.rekuts.travelagency.service;

import by.rekuts.travelagency.dao.subjects.User;
import by.rekuts.travelagency.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Test
    public void insertTest(){
        UserServiceImpl userService = mock(UserServiceImpl.class);
        doNothing().when(userService).insert(new User());
        userService.insert(new User());
        userService.insert(new User());
        verify(userService, times(2)).insert(any());
    }

    @Test
    public void deleteTest() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        doNothing().when(userService).insert(new User());
        userService.delete(6);
        userService.delete(8);
        verify(userService, times(2)).delete(anyInt());
    }

    @Test
    public void getReviewByIdTestTrue() {
        List<User> users = Arrays.asList(
                new User(1, "superlogin1", "thisispassword"),
                new User(2, "superlogin2", "thisispassword"),
                new User(3, "superlogin3", "thisispassword"));
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getUserById(2)).thenReturn(users.get(1));
        User user = userService.getUserById(2);
        assertEquals("superlogin2", user.getLogin());
    }

    @Test
    public void getReviewByIdTestFalse() {
        List<User> users = Arrays.asList(
                new User(1, "superlogin1", "thisispassword"),
                new User(2, "superlogin2", "thisispassword"),
                new User(3, "superlogin3", "thisispassword"));
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getUserById(2)).thenReturn(users.get(1));
        User user = userService.getUserById(2);
        assertNotEquals("superlogin1", user.getLogin());
    }

    @Test
    public void getAllReviewsTestTrue() {
        List<User> users = Arrays.asList(
                new User(1, "superlogin1", "thisispassword"),
                new User(2, "superlogin2", "thisispassword"),
                new User(3, "superlogin3", "thisispassword"));
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getAllUsers()).thenReturn(users);
        List<User> tourList = userService.getAllUsers();
        assertEquals(3, tourList.size());
    }

    @Test
    public void getAllReviewsTestFalse() throws SQLException {
        List<User> users = Arrays.asList(
                new User(1, "superlogin1", "thisispassword"),
                new User(2, "superlogin2", "thisispassword"),
                new User(3, "superlogin3", "thisispassword"));
        UserServiceImpl userService = mock(UserServiceImpl.class);
        when(userService.getAllUsers()).thenReturn(users);
        List<User> tourList = userService.getAllUsers();
        assertNotEquals(0, tourList.size());
    }
}
