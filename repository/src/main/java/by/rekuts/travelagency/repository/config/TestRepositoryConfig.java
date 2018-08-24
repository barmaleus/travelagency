package by.rekuts.travelagency.repository.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

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
public class TestRepositoryConfig {

    @Bean
    @Profile("testScope")
    public DataSource travelAgencyDataSource() throws IOException, SQLException {
        final FlywayPreparer preparer = FlywayPreparer.forClasspathLocation("db");
        EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.start();
        final DataSource postgresDatabase = embeddedPostgres.getPostgresDatabase();
        preparer.prepare(postgresDatabase);
        return postgresDatabase;
    }

}