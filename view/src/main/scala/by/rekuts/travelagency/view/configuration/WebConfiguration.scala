package by.rekuts.travelagency.view.configuration

import org.springframework.context.MessageSource
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.format.FormatterRegistry
import org.springframework.web.multipart.MultipartResolver
import org.springframework.web.multipart.support.StandardServletMultipartResolver
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation._
import org.springframework.web.servlet.i18n.{CookieLocaleResolver, LocaleChangeInterceptor}
import org.springframework.web.servlet.theme.ThemeChangeInterceptor
import org.springframework.web.servlet.view.freemarker.{FreeMarkerConfigurer, FreeMarkerViewResolver}

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Array(
  "by.rekuts.travelagency.view",
  "by.rekuts.travelagency.service"))
class WebConfiguration extends WebMvcConfigurerAdapter {
  override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
  }

  override def addViewControllers(registry: ViewControllerRegistry): Unit = {
    registry.addViewController("/sign-in").setViewName("sign-in")
    registry.addViewController("/sign-up").setViewName("sign-up")
    registry.addViewController("/import").setViewName("import")
    registry.addViewController("/importStatus").setViewName("importStatus")
    registry.addViewController("/new-user").setViewName("create/new-user")
    registry.addViewController("/new-country").setViewName("create/new-country")
  }

  @Bean
  def freemarkerConfig: FreeMarkerConfigurer = {
    val freeMarkerConfigurer = new FreeMarkerConfigurer
    freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/")
    freeMarkerConfigurer.setDefaultEncoding("UTF-8")
    freeMarkerConfigurer
  }

  @Bean
  def viewResolver: FreeMarkerViewResolver = {
    val viewResolver = new FreeMarkerViewResolver
    viewResolver.setSuffix(".ftl")
    viewResolver.setCache(true)
    viewResolver.setContentType("text/html;charset=UTF-8")
    viewResolver.setOrder(0)
    viewResolver
  }

  @Bean
  def multipartResolver: MultipartResolver = new StandardServletMultipartResolver

  @Bean(Array("messageSource"))
  def messageSource: MessageSource = {
    val messageSource = new ReloadableResourceBundleMessageSource
    messageSource.setBasename("classpath:locale/messages")
    messageSource.setDefaultEncoding("UTF-8")
    messageSource.setUseCodeAsDefaultMessage(true)
    messageSource
  }

  @Bean
  def localeResolver: LocaleResolver = new CookieLocaleResolver

  override def addInterceptors(registry: InterceptorRegistry): Unit = {
    val themeChangeInterceptor = new ThemeChangeInterceptor
    themeChangeInterceptor.setParamName("theme")
    registry.addInterceptor(themeChangeInterceptor)
    val localeChangeInterceptor = new LocaleChangeInterceptor
    localeChangeInterceptor.setParamName("lang")
    registry.addInterceptor(localeChangeInterceptor)
  }

  override def addFormatters(registry: FormatterRegistry): Unit = {
    val formatter = new ObjectFormatter
    registry.addFormatter(formatter.userFormatter)
    registry.addFormatter(formatter.tourFormatter)
    registry.addFormatter(formatter.countryFormatter)
    registry.addFormatter(formatter.hotelFormatter)
    registry.addFormatter(formatter.localDateFormatter)
  }
}
