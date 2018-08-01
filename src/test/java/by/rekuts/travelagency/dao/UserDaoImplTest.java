package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
@Transactional
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    TourDao tourDao;

    @Test
    public void insertTestTrue() {
        User user = new User();
        user.setLogin("Userok");
        user.setPassword("veryDifficultPassword");
        int countFirst = userDao.getAllUsers().size();
        userDao.insert(user);
        int countLast = userDao.getAllUsers().size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteUserTestTrue() {
        int countFirst = userDao.getAllUsers().size();
        userDao.delete(1);
        int countLast = userDao.getAllUsers().size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getUserByIdTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, 2);
        log.info("User was found: " + user.getLogin());
        Assert.assertEquals(user.getLogin(), userDao.getUserById(2).getLogin());
    }

    @Test
    public void getUserByIdWithToursTest() {
        User user = userDao.getUserById(101);
        log.info(user.getTours().toString());
        Assert.assertEquals(2, user.getTours().size());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userDao.getAllUsers();
       Assert.assertEquals(100, users.size());
    }
}
