package by.rekuts.travelagency.logic.service.impl;

import by.rekuts.travelagency.logic.repository.Specification;
import by.rekuts.travelagency.logic.repository.UserRepository;
import by.rekuts.travelagency.logic.domain.User;
import by.rekuts.travelagency.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.insert(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getList(Specification specification) {
        return userRepository.getList(specification);
    }
}
