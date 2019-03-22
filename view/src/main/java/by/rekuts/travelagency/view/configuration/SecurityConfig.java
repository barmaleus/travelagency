package by.rekuts.travelagency.view.configuration;

import by.rekuts.travelagency.repository.UserSpecification;
import by.rekuts.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/", "/sign-in", "/sign-up", "/tours", "/reg-user").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/error")
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .successHandler((req,res,auth)->{
                    var specification = new UserSpecification();
                    specification.setLogin(auth.getName());
                    var curUser = userService.getList(specification).get(0);
                    req.getSession().setAttribute("sesUserId", curUser.getId());
                    res.sendRedirect("/");
                })
                .failureHandler((req,res,exp)->{
                    req.setAttribute("message", "Invalid username or password.");
                    res.sendRedirect("/sign-in");
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/sign-out")
                .logoutSuccessHandler((req,res,auth)->{
                    req.setAttribute("message", "You are logged out successfully.");
                    res.sendRedirect("/sign-in");
                })
                .permitAll()
                .and()
                .csrf().disable();
    }
}