package by.rekuts.travelagency.repository.config

import java.io.IOException
import java.sql.SQLException

import com.opentable.db.postgres.embedded.{EmbeddedPostgres, FlywayPreparer}
import javax.sql.DataSource
import org.springframework.context.annotation._
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@ComponentScan(Array("by.rekuts.travelagency"))
@EnableTransactionManagement
@PropertySource(Array("classpath:db/V1.1__Schema.sql", "classpath:db/V1.2__Init-data.sql"))
@EnableAspectJAutoProxy
class TestRepositoryConfig {
  @Bean
  @Profile(Array("testScope"))
  def getDataSource: DataSource = {
    val preparer = FlywayPreparer.forClasspathLocation("db")
    var postgresDatabase: DataSource = null
    try {
      val embeddedPostgres = EmbeddedPostgres.start
      postgresDatabase = embeddedPostgres.getPostgresDatabase
      preparer.prepare(postgresDatabase)
    } catch {
      case e@(_: IOException | _: SQLException) =>
        println(e)
    }
    postgresDatabase
  }
}
