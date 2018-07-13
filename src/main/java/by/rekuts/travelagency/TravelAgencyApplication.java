package by.rekuts.travelagency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import by.rekuts.travelagency.dao.subjects.Country;
import by.rekuts.travelagency.service.CountryService;
import by.rekuts.travelagency.dao.CountryDao;

@SpringBootApplication
@ComponentScan("by.rekuts.travelagency.service.impl, by.rekuts.travelagency.dao.impl")
public class TravelAgencyApplication {

	@Autowired
	CountryService countryService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TravelAgencyApplication.class, args);
		CountryService countryService = context.getBean(CountryService.class);
		/*
		 * Create Country
		 */

		// Country 1
//		Country country = new Country();
//		country.setId(201);
//		country.setName("BelarusBlyat");

		// Insert a country to DB
//		countryDao.insert(country);
//

		// Load All Country and display
		List<Country> list = countryService.getAllCountries();
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}

		// Get Country By Id
		System.out.println("=============Get Country By Id");
		Country c = countryService.getCountryById(50);
		System.out.println(c);

		System.out.println("#######################################");
		System.out.println("Done!!!");
		System.out.println("#######################################");
	}
}
