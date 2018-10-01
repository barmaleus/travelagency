package by.rekuts.travelagency.repository.config;

import by.rekuts.travelagency.repository.CountryRepository;
import by.rekuts.travelagency.repository.impl.CountryRepositoryImpl;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan({
        "by.rekuts.travelagency"
})
@EnableTransactionManagement
@PropertySource({
        "classpath:db/V1.1__Schema.sql",
        "classpath:db/V1.2__Init-data.sql"
})
@EnableAspectJAutoProxy
@Profile("testScope")
public class TestRepositoryConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        try {
            factoryBean.setDataSource(getDataSource());
        } catch (IOException | SQLException e) {}
        factoryBean.setPersistenceUnitName("testScope");
        factoryBean.setPackagesToScan("by.rekuts.travelagency");
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    @Bean
    public DataSource getDataSource() throws IOException, SQLException {
        final FlywayPreparer preparer = FlywayPreparer.forClasspathLocation("db");
        EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.start();
        final DataSource postgresDatabase = embeddedPostgres.getPostgresDatabase();
        preparer.prepare(postgresDatabase);
        return postgresDatabase;
    }
    @Bean
    public PlatformTransactionManager txManager(){
        return new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
    }
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }
    @Bean
    public CountryRepository countryRepository() {
        return new CountryRepositoryImpl();
    }
}