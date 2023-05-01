package das.spring.security.app.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Value("${db.datasource.internal.url}")
    private String jdbcUrl;

    @Value("${db.datasource.internal.username}")
    private String username;

    @Value("${db.datasource.internal.password}")
    private String password;

    @Value("${db.datasource.internal.driver-class-name}")
    private String driverClassName;


    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setPoolName("myHikariPool");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
