package by.rekuts.travelagency.view.configuration

import by.rekuts.travelagency.service.config.ServiceConfig
import javax.servlet.{MultipartConfigElement, ServletRegistration}
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  override protected def createRootApplicationContext: WebApplicationContext = {
    val context = super.createRootApplicationContext
    context.getEnvironment.asInstanceOf[ConfigurableEnvironment].setActiveProfiles("travelAgencyPU")
    context
  }

  override protected def getRootConfigClasses: Array[Class[_]] = Array[Class[_]](classOf[ServiceConfig], classOf[WebConfiguration], classOf[SecurityConfig])

  override protected def getServletConfigClasses: Array[Class[_]] = null

  override protected def getServletMappings: Array[String] = Array[String]("/")

  override protected def customizeRegistration(registration: ServletRegistration.Dynamic): Unit = {
    registration.setMultipartConfig(new MultipartConfigElement("/", 2097152, 4194304, 2097152))
  }
}
