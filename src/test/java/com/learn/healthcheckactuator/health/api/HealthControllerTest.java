package com.learn.healthcheckactuator.health.api;
import com.learn.healthcheckactuator.health.properties.HealthProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.actuate.health.Health;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


public class HealthControllerTest {

    @InjectMocks
    private HealthController healthController;

    @Mock
    private HealthProperties healthProperties;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test purpose  - Test health API Test expected result - Health message received successfully Test type
     * - Positive
     */
    @Test
    public void testHealthMessage() {
        given(healthProperties.getApplicationName()).willReturn("telemetry-svc");
        Health health = healthController.health();
        Assertions.assertNotNull(health);
        assertEquals("telemetry-svc", health.getDetails().get("Micro Service Name").toString(), "Health message is not correct");
    }
}
