//package by.rekuts.travelagency.repository;
//
//import by.rekuts.travelagency.domain.User;
//import by.rekuts.travelagency.repository.config.TestRepositoryConfig;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestRepositoryConfig.class)
//@ActiveProfiles("testScope")
//@Transactional
//public class UserRepositoryImplTest {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    TourRepository tourRepository;
//
//    @Test
//    public void insertTestTrue() {
//        User user = new User();
//        user.setLogin("Userok");
//        user.setPassword("veryDifficultPassword");
//        int countFirst = userRepository.getList(new UserSpecification()).size();
//        userRepository.insert(user);
//        int countLast = userRepository.getList(new UserSpecification()).size();
//        Assert.assertEquals(1, countLast - countFirst);
//    }
//
//    @Test
//    public void deleteUserTestTrue() {
//        int countFirst = userRepository.getList(new UserSpecification()).size();
//        userRepository.delete(1);
//        int countLast = userRepository.getList(new UserSpecification()).size();
//        Assert.assertEquals(1, countFirst - countLast);
//    }
//
//    @Test
//    public void getUserByIdTest() {
//        Assert.assertEquals("brownbutterfly205", userRepository.getList(new UserSpecification(2)).get(0).getLogin().trim());
//    }
//
//    @Test
//    public void getUserByIdWithToursTest() {
//        User user = userRepository.getList(new UserSpecification(101)).get(0);
//        Assert.assertEquals(2, user.getTours().size());
//    }
//
//    @Test
//    public void getAllUsersTest() {
//        List<User> users = userRepository.getList(new UserSpecification());
//       Assert.assertEquals(103, users.size());
//    }
//}
