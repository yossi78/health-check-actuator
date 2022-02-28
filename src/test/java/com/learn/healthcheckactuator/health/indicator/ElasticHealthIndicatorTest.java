package com.learn.healthcheckactuator.health.indicator;

import com.learn.healthcheckactuator.HealthCheckActuatorApplication;
import com.learn.healthcheckactuator.properties.ElasticSearchProperties;
import com.redbend.telemery.health.exception.ElasticSearchClusterNotReadyException;
import com.redbend.telemery.health.dto.ElasticSearchClusterHealth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class ElasticHealthIndicatorTest {

    @Mock
    private ElasticSearchProperties elasticSearchProperties;

    @Mock
    private ElasticSearchClusterHealth elasticSearchClusterHealth;

    @InjectMocks
    public ElasticHealthIndicator sut;



    @BeforeEach
    void setUp() {
        openMocks(this);
    }


    /**
     * The Test check the elastic search readiness and verify it via falilure absence
     */
    @Test
    public void clusterIndicateTest() {
        ConfigurableApplicationContext run = SpringApplication.run(HealthCheckActuatorApplication.class, "");
        List<String> list = run.getBean(ElasticSearchProperties.class).getClusterNodes();
        when(elasticSearchProperties.getClusterNodes()).thenReturn(list);
        sut.checkElasticSearchReadiness();
    }

    @Test
    public void testReadinessFailure() {
        when(elasticSearchClusterHealth.getStatus()).thenReturn("red");
        Assertions.assertThrows(ElasticSearchClusterNotReadyException.class,
                () -> {
                    sut.checkElasticSearchReadiness();
                });
    }


}