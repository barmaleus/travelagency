package by.rekuts.travelagency.repository.config

import java.util.Properties

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import javax.sql.DataSource
import org.springframework.context.annotation.{Bean, Configuration, EnableAspectJAutoProxy, Profile}
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.orm.jpa.{JpaTransactionManager, LocalContainerEntityManagerFactoryBean}
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
class JpaConf {

  @Bean
  def getEntityManagerFactoryBean: LocalContainerEntityManagerFactoryBean = {
    val factoryBean = new LocalContainerEntityManagerFactoryBean
    val vendorAdapter = new HibernateJpaVendorAdapter
    factoryBean.setJpaVendorAdapter(vendorAdapter)
    factoryBean.setDataSource(getDataSource)
    factoryBean.setPersistenceUnitName("travelAgencyPU")
    factoryBean.setPackagesToScan("by.rekuts.travelagency")
    factoryBean.setJpaProperties(jpaProperties)
    factoryBean
  }

  @Bean
  @Profile(Array("travelAgencyPU"))
  def getDataSource: DataSource = {
    val config = new HikariConfig
    config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource")
    config.setUsername("postgres")
    config.setPassword("topsecret")
    config.addDataSourceProperty("databaseName", "travelagency")
    config.addDataSourceProperty("serverName", "127.0.0.1")
    new HikariDataSource(config)
  }

  @Bean
  def txManager = new JpaTransactionManager(getEntityManagerFactoryBean.getObject)

  private def jpaProperties = {
    val properties = new Properties
    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect")
    properties.put("hibernate.show_sql", "true")
    properties.put("hibernate.format_sql", "true")
    properties
  }
}
