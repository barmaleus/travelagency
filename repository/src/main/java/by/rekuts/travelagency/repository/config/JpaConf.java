//package by.rekuts.travelagency.repository.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.*;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Slf4j
//@Configuration
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
//public class JpaConf {
//
////    @Bean
////    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
////        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
////        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////        factoryBean.setJpaVendorAdapter(vendorAdapter);
////        factoryBean.setDataSource(getDataSource());
////        factoryBean.setPersistenceUnitName("dev");
////        factoryBean.setPackagesToScan("by.rekuts.travelagency");
////        factoryBean.setJpaProperties(jpaProperties());
////        return factoryBean;
////    }
////
////    @Bean
////    @Profile("dev")
////    public DataSource getDataSource() {
////        HikariConfig config = new HikariConfig();
////        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
////        config.setUsername("postgres");
////        config.setPassword("topsecret");
////        config.addDataSourceProperty("databaseName", "travelagency");
////        config.addDataSourceProperty("serverName", "db");
////        return new HikariDataSource(config);
////    }
////
////    @Bean
////    public PlatformTransactionManager txManager() {
////        return new JpaTransactionManager(
////                getEntityManagerFactoryBean().getObject());
////    }
////
////    private Properties jpaProperties() {
////        Properties properties = new Properties();
////        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
////        properties.put("hibernate.show_sql", "true");
////        properties.put("hibernate.format_sql", "true");
////        return properties;
////    }
//}
