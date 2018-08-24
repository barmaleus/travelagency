package by.rekuts.travelagency.service.config;

import by.rekuts.travelagency.repository.config.JpaConf;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaConf.class)
@ComponentScan(basePackages = {
        "by.rekuts.travelagency.repository",
        "by.rekuts.travelagency.service",
        "by.rekuts.travelagency.domain"
})
public class ServiceConfig {

}
