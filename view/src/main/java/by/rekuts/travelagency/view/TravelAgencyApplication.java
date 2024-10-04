package by.rekuts.travelagency.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "by.rekuts.travelagency.repository",
        "by.rekuts.travelagency.service",
        "by.rekuts.travelagency.view"
})
@EntityScan(basePackages = {
        "by.rekuts.travelagency.domain"
})
@EnableJpaRepositories(basePackages = "by.rekuts.travelagency.repository")
public class TravelAgencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }
}
