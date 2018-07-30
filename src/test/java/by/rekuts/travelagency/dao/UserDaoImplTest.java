package by.rekuts.travelagency.dao;

import by.rekuts.travelagency.config.JpaConf;
import by.rekuts.travelagency.dao.subjects.User;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConf.class)
public class UserDaoImplTest {

//    @ClassRule
//    public static PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @Autowired
    UserDao userDao;

    @Test
    @Ignore
    public void insertTestTrue() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        User user = new User();
        user.setLogin("Userok");
        user.setPassword("veryDifficultPassword");
        em.persist(user);
        //todo выполняется, но я не вижу изменений в базе rollbackается
    }

    @Test
    @Ignore
    public void deleteUserTestTrue() {
        int countFirst = userDao.getAllUsers().size();
        userDao.delete(5555);
        int countLast = userDao.getAllUsers().size();
        Assert.assertEquals(1, countFirst - countLast);
    }   //todo check

    //OK
    @Test
    public void getUserByIdTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, 2);
        System.out.println(user.getLogin());
        Assert.assertEquals(user.getLogin(), userDao.getUserById(2).getLogin());
    }

    //OK
    @Test
    public void getAllUsersTest() {
        List<User> users = userDao.getAllUsers();
       Assert.assertEquals(100, users.size());
    }
}
