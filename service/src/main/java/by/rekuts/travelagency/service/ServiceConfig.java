package by.rekuts.travelagency.service;

import by.rekuts.travelagency.domain.config.JpaConf;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JpaConf.class})
@ComponentScan(basePackages = {"by.rekuts.travelagency.repository", "by.rekuts.travelagency.service"})
public class ServiceConfig {

}
