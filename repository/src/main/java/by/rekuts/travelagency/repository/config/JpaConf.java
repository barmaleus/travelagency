package by.rekuts.travelagency.repository.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class JpaConf {

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        var vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPersistenceUnitName("travelAgencyPU");
        factoryBean.setPackagesToScan("by.rekuts.travelagency");
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    @Bean
    @Profile("travelAgencyPU")
    public DataSource getDataSource() {
        var config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setUsername("postgres");
        config.setPassword("topsecret");
        config.addDataSourceProperty("databaseName", "travelagency");
        config.addDataSourceProperty("serverName", "127.0.0.1");
        return new HikariDataSource(config);
    }
    @Bean
    public PlatformTransactionManager txManager(){
        return new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
    }
    private Properties jpaProperties() {
        var properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }
}