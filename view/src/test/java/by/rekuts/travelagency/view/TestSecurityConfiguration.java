package by.rekuts.travelagency.view;

import by.rekuts.travelagency.domain.User;
import by.rekuts.travelagency.repository.UserSpecification;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {
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
                    UserSpecification specification = new UserSpecification();
                    specification.setLogin(auth.getName());
                    User curUser = new User();
                    curUser.setId(69);
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

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.debug(true);
    }
}