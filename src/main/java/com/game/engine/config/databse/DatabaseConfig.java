package com.game.engine.config.databse;

import com.game.engine.config.EnvironmentConfiguration;
import com.game.engine.config.model.DatabaseConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean(name = "gameDS")
    public DataSource getProductDataSource(EnvironmentConfiguration environmentConfiguration) {
        DatabaseConfiguration databaseConfiguration = environmentConfiguration.getDatabaseConfiguration();
        return setUpDataBaseConnection(databaseConfiguration);
    }

    private DataSource setUpDataBaseConnection(DatabaseConfiguration databaseConfiguration) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseConfiguration.getUrl());
        hikariConfig.setUsername(databaseConfiguration.getUserName());
        hikariConfig.setPassword(databaseConfiguration.getPassword());
        hikariConfig.setMaximumPoolSize(databaseConfiguration.getMaxPoolSize());
        hikariConfig.setDriverClassName(databaseConfiguration.getDriverClassName());
        hikariConfig.setConnectionTimeout(databaseConfiguration.getConnectionTimeOut());
        return new HikariDataSource(hikariConfig);
    }

}
