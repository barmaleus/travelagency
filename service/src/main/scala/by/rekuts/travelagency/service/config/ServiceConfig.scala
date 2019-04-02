package by.rekuts.travelagency.service.config

import by.rekuts.travelagency.repository.config.JpaConf
import org.springframework.context.annotation.{ComponentScan, Configuration, Import}

@Configuration
@Import(Array(classOf[JpaConf]))
@ComponentScan(basePackages = Array(
  "by.rekuts.travelagency.repository",
  "by.rekuts.travelagency.service",
  "by.rekuts.travelagency.domain"
))
class ServiceConfig
