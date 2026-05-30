package com.game.engine.config;

import com.game.engine.config.model.DatabaseConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "game.engine")
public class EnvironmentConfiguration {
    private DatabaseConfiguration databaseConfiguration;
}
