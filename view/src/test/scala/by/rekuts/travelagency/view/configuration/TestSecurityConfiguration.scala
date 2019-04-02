package by.rekuts.travelagency.view.configuration

import by.rekuts.travelagency.domain.User
import by.rekuts.travelagency.repository.UserSpecification
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.{HttpSecurity, WebSecurity}
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.core.{Authentication, AuthenticationException}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class TestSecurityConfiguration extends WebSecurityConfigurerAdapter {
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
      def successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, auth: Authentication) = {
        val specification = new UserSpecification
        specification.login_$eq(auth.getName)
        val curUser = new User
        curUser.id_$eq(69)
        req.getSession.setAttribute("sesUserId", curUser.id)
        res.sendRedirect("/")
      }
      successfulAuthentication(req, res, auth)
    }).failureHandler((req: HttpServletRequest, res: HttpServletResponse, exp: AuthenticationException) => {
      def unsuccessfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, exp: AuthenticationException) = {
        req.setAttribute("message", "Invalid username or password.")
        res.sendRedirect("/sign-in")
      }
      unsuccessfulAuthentication(req, res, exp)
    }).permitAll.and.logout.logoutUrl("/sign-out").logoutSuccessHandler((req: HttpServletRequest, res: HttpServletResponse, auth: Authentication) => {
      def logout(req: HttpServletRequest, res: HttpServletResponse, auth: Authentication) = {
        req.setAttribute("message", "You are logged out successfully.")
        res.sendRedirect("/sign-in")
      }
      logout(req, res, auth)
    }).permitAll.and.csrf.disable
  }

  @throws[Exception]
  override def configure(web: WebSecurity): Unit = {
    web.debug(true)
  }
}
