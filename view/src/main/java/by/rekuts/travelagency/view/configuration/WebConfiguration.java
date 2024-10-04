package by.rekuts.travelagency.view.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {
//        "by.rekuts.travelagency.view",
//        "by.rekuts.travelagency.service"
//})
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.freeMarker();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sign-in").setViewName("sign-in-page");
        registry.addViewController("/sign-up").setViewName("sign-up");
        registry.addViewController("/import").setViewName("import");
        registry.addViewController("/importStatus").setViewName("importStatus");
        registry.addViewController("/new-user").setViewName("create/new-user");
        registry.addViewController("/new-country").setViewName("create/new-country");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
        themeChangeInterceptor.setParamName("theme");
        registry.addInterceptor(themeChangeInterceptor);

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        ObjectFormatter formatter = new ObjectFormatter();
        registry.addFormatter(formatter.userFormatter());
        registry.addFormatter(formatter.tourFormatter());
        registry.addFormatter(formatter.countryFormatter());
        registry.addFormatter(formatter.hotelFormatter());
        registry.addFormatter(formatter.localDateFormatter());
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        // Set your template loader path if not defined in application.properties
        // freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");

        // Add custom variables to be exposed in FreeMarker templates
        Map<String, Object> variables = new HashMap<>();
        variables.put("security", new SecurityExpressionObject());

        freeMarkerConfigurer.setFreemarkerVariables(variables);
        return freeMarkerConfigurer;
    }

    // Custom Security Object to expose Spring Security expressions
    public static class SecurityExpressionObject {

        public boolean hasRole(String role) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                return authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
            }
            return false;
        }

        public boolean isAuthenticated() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication != null && authentication.isAuthenticated();
        }
    }
}
