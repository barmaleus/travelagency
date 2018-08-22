package by.rekuts.travelagency.view.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/", "/sign-in", "/sign-up", "/tours", "/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/j_spring_security_check")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
                    for (GrantedAuthority authority : auth.getAuthorities()) {
                        System.out.println(authority.getAuthority());
                    }
//                    System.out.println(auth.getName());
                    req.getSession().setAttribute("sesUser", auth.getPrincipal());
//                    System.out.println(auth.getPrincipal());
                    res.sendRedirect("/"); // Redirect user to index/home page
                })
//    .defaultSuccessUrl("/")   // URL, where user will go after authenticating successfully.
                // Skipped if successHandler() is used.
                .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
                    String errMsg="";
                    if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
                        errMsg="Invalid username or password.";
                    }else{
                        errMsg="Unknown error - "+exp.getMessage();
                    }
                    req.getSession().setAttribute("message", errMsg);
                    res.sendRedirect("/sign-in"); // Redirect user to login page with error message.
                })
//    .failureUrl("/login?error")   // URL, where user will go after authentication failure.
                //  Skipped if failureHandler() is used.
                .permitAll() // Allow access to any URL associate to formLogin()
                .and()
                .logout()
                .logoutUrl("/sign-out")   // Specifies the logout URL, default URL is '/logout'
                .logoutSuccessHandler((req,res,auth)->{   // Logout handler called after successful logout
                    req.getSession().setAttribute("message", "You are logged out successfully.");
                    res.sendRedirect("/sign-in"); // Redirect user to login page with message.
                })
//    .logoutSuccessUrl("/login") // URL, where user will be redirect after successful
                //  logout. Ignored if logoutSuccessHandler() is used.
                .permitAll() // Allow access to any URL associate to logout()
                .and()
                .csrf().disable();
    }
}