//package by.rekuts.travelagency.service.impl;
//
//import by.rekuts.travelagency.logic.service.CustomSecurityService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
///**
// * Implementaion of {@link CustomSecurityService} interface.
// */
//
//@Slf4j
//@Service
//public class CustomSecurityServiceImpl implements CustomSecurityService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public String findLoggedInLogin() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//        }
//        return null;
//    }
//
//    @Override
//    public void autoLogin(String login, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        authenticationManager.authenticate(authenticationToken);
//        if(authenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        log.info(String.format("%s autologged successfully", login));
//    }
//}
