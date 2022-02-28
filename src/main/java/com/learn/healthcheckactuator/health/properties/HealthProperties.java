package com.learn.healthcheckactuator.health.properties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
@Data
public class HealthProperties {

    @Value("${management.metrics.tags.application}")
    private String applicationName;


}
