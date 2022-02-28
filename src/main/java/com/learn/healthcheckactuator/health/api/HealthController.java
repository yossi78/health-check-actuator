package com.learn.healthcheckactuator.health.api;
import com.learn.healthcheckactuator.health.properties.HealthProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author Yossi Tal
 */

@Component
@Slf4j
public class HealthController implements HealthIndicator {


    private HealthProperties healthProperties;

    /**
     * Init constructor which get the properties class for getting the required keys
     * @param healthProperties
     */
    @Autowired
    public HealthController(HealthProperties healthProperties) {
        this.healthProperties = healthProperties;
    }

    /** Health check method verify application health indicate via API call
     * @return Health Object */

    @Override
    public Health health() {

        log.debug("Received Health request");
        return Health
                .up()
                .withDetail("Micro Service Name", healthProperties.getApplicationName())
                .build();
    }




}
