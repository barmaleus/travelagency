package by.rekuts.travelagency.service.impl;

import by.rekuts.travelagency.domain.Tour;
import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserRepository;
import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<User> getList(Specification<User> specification) {
        return userRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) {
        UserSpecification specification = new UserSpecification();
        specification.setLogin(login);
        User user = userRepository.findAll(specification).get(0);
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
        Optional<User> temp = userRepository.findById(user.getId());
        temp.ifPresent(u -> {
            u.getTours().add(tour);
            userRepository.save(u);
        });
    }

    @Override
    public void removeTourFromFavorites(User user, Tour tour) {
        Optional<User> temp = userRepository.findById(user.getId());
        temp.ifPresent(u -> {
            u.getTours().remove(tour);
            userRepository.save(u);
        });
    }
}
