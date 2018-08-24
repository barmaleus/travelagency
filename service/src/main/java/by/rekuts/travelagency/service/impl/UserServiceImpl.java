package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.repository.Specification;
import by.rekuts.travelagency.repository.UserRepository;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public void insert(User user) {
        userRepository.insert(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Transactional
    @Override
    public List<User> getList(Specification specification) {
        return userRepository.getList(specification);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserSpecification specification = new UserSpecification();
        specification.setLogin(login);
        User user = userRepository.getList(specification).get(0);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
            );
        }
        throw new UsernameNotFoundException("User '" + login + "' not found");
    }
}