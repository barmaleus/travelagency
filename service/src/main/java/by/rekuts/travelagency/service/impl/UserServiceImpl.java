package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.domain.Tour;
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

@Service(value = "userDetailService")
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
    public void update(User user) {
        userRepository.update(user);
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

    @Override
    public void addTourToFavorites(User user, Tour tour) {
        userRepository.addTourToFavorites(user, tour);
    }

    @Override
    public void removeTourFromFavorites(User user, Tour tour) {
        userRepository.removeTourFromFavorites(user, tour);
    }
}
