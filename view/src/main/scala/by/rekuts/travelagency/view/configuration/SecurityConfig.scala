package by.rekuts.travelagency.view.configuration

import by.rekuts.travelagency.repository.UserSpecification
import by.rekuts.travelagency.service.UserService
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.core.{Authentication, AuthenticationException}
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(userDetailsService: UserDetailsService, userService: UserService) extends WebSecurityConfigurerAdapter {

  @throws[Exception]
  override def configure(auth: AuthenticationManagerBuilder): Unit = {
    auth.userDetailsService(userDetailsService)
  }

  @throws[Exception]
  override protected def configure(http: HttpSecurity): Unit = {
    http
      .authorizeRequests
      .antMatchers("/resources/**").permitAll
      .antMatchers("/", "/sign-in", "/sign-up", "/tours", "/reg-user").permitAll
      .anyRequest.authenticated
      .and
      .exceptionHandling.accessDeniedPage("/error")
      .and
      .formLogin
      .loginPage("/sign-in")
      .loginProcessingUrl("/j_spring_security_check")
      .usernameParameter("j_username")
      .passwordParameter("j_password")
      .successHandler((req: HttpServletRequest, res: HttpServletResponse, auth: Authentication) => {
      def successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, auth: Authentication): Unit = {
        val specification = new UserSpecification
        specification.login_$eq(auth.getName)
        val curUser = userService.getList(specification).get(0)
        req.getSession.setAttribute("sesUserId", curUser.id)
        res.sendRedirect("/")
      }
      successfulAuthentication(req, res, auth)
    }).failureHandler((req: HttpServletRequest, res: HttpServletResponse, exp: AuthenticationException) => {
      def unsuccessfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, exp: AuthenticationException): Unit = {
        req.setAttribute("message", "Invalid username or password.")
        res.sendRedirect("/sign-in")
      }
      unsuccessfulAuthentication(req, res, exp)
    }).permitAll.and.logout.logoutUrl("/sign-out").logoutSuccessHandler((req: HttpServletRequest, res: HttpServletResponse, auth: Authentication) => {
      def logout(req: HttpServletRequest, res: HttpServletResponse, auth: Authentication): Unit = {
        req.setAttribute("message", "You are logged out successfully.")
        res.sendRedirect("/sign-in")
      }
      logout(req, res, auth)
    }).permitAll.and.csrf.disable
  }
}
