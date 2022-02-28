package com.learn.healthcheckactuator.health.api;

import com.learn.healthcheckactuator.health.indicator.ElasticHealthIndicator;
import com.learn.healthcheckactuator.health.indicator.JmsHealthIndicator;
import com.redbend.telemery.health.exception.ElasticSearchClusterNotReadyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.openMocks;


public class ReadinessControllerTest {

    @Mock
    public ElasticHealthIndicator elasticHealthIndicator;

    @Mock
    public JmsHealthIndicator jmsHealthIndicator;

    @InjectMocks
    public ReadinessController sut = new ReadinessController(elasticHealthIndicator, jmsHealthIndicator);


    @BeforeEach
    void setUp() {
        openMocks(this);
    }


    /**
     * The Test check the readiness of the application and assert it with the returned result from method
     */
    @Test
    public void testReadiness() {
        String result = sut.checkReadiness();
        Assertions.assertEquals("Health Check Readiness has been succeed", result);
    }


    @Test
    public void testReadinessFailure() {
        Mockito.doThrow(new ElasticSearchClusterNotReadyException("Health Check Readiness has been failed"))
                .when(elasticHealthIndicator).checkElasticSearchReadiness();
        Assertions.assertThrows(ElasticSearchClusterNotReadyException.class,
                () -> {
                    sut.checkReadiness();
                });
    }


}