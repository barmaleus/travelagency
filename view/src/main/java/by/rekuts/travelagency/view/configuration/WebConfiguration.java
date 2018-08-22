package by.rekuts.travelagency.view.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "by.rekuts.travelagency.view.controller",
        "by.rekuts.travelagency.service"
})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/sign-in").setViewName("sign-in");
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {

        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");

        return freeMarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver viewResolver() {

        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setSuffix(".ftl");
        viewResolver.setCache(false);   //todo Set to true during production
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setOrder(0);

        return viewResolver;
    }
}
