package by.rekuts.travelagency;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.CountryService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("by.rekuts.travelagency.service.impl, by.rekuts.travelagency.dao.impl")
public class TravelAgencyApplication {

    @Autowired
    CountryService countryService;

    private final static Logger LOGGER = LoggerFactory.getLogger(TravelAgencyApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TravelAgencyApplication.class, args);
        CountryService countryService = context.getBean(CountryService.class);

        LOGGER.info("Info log message");
        // Country 1
		Country country = new Country();
		country.setId(201);
		country.setName("Belarus");

//		 Insert a country to DB
//		countryService.insert(country);
//        countryService.delete(country.getId());


        // Load All Country and display
        List<Country> list = countryService.getAllCountries();
        for(int i = 0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }

        // Get Country By Id
        System.out.println("=============Get Country By Id");
        Country c = countryService.getCountryById(48);
        System.out.println(c);

        System.out.println("#######################################");
        System.out.println("Done!!!");
        System.out.println("#######################################");
    }
}
