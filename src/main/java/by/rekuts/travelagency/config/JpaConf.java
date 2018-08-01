package by.rekuts.travelagency.config;

import java.util.Properties;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
@ComponentScan(basePackages = "by.rekuts.travelagency")
public class JpaConf {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPersistenceUnitName("travelAgencyPU");
        factoryBean.setPackagesToScan("by.rekuts.travelagency");
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setUsername("postgres");
        config.setPassword("topsecret");
        config.addDataSourceProperty("databaseName", "travelagency");
        config.addDataSourceProperty("serverName", "127.0.0.1");

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    @Bean
    public PlatformTransactionManager txManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
        return jpaTransactionManager;
    }
    private Properties jpaProperties() {
        Properties properties = new Properties();;
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }
}