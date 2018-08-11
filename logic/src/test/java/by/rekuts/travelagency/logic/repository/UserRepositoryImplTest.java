package by.rekuts.travelagency.logic.repository;

import by.rekuts.travelagency.logic.config.TestRepositoryConfig;
import by.rekuts.travelagency.logic.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRepositoryConfig.class)
@ActiveProfiles("testScope")
@Transactional
public class UserRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TourRepository tourRepository;

    @Test
    public void insertTestTrue() {
        User user = new User();
        user.setLogin("Userok");
        user.setPassword("veryDifficultPassword");
        int countFirst = userRepository.getList(new UserSpecification()).size();
        userRepository.insert(user);
        int countLast = userRepository.getList(new UserSpecification()).size();
        Assert.assertEquals(1, countLast - countFirst);
    }

    @Test
    public void deleteUserTestTrue() {
        int countFirst = userRepository.getList(new UserSpecification()).size();
        userRepository.delete(1);
        int countLast = userRepository.getList(new UserSpecification()).size();
        Assert.assertEquals(1, countFirst - countLast);
    }

    @Test
    public void getUserByIdTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("travelAgencyPU");
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, 2);
        Assert.assertEquals(user.getLogin(), userRepository.getList(new UserSpecification(2)).get(0).getLogin());
    }

    @Test
    public void getUserByIdWithToursTest() {
        User user = userRepository.getList(new UserSpecification(101)).get(0);
        Assert.assertEquals(2, user.getTours().size());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userRepository.getList(new UserSpecification());
       Assert.assertEquals(101, users.size());
    }
}
