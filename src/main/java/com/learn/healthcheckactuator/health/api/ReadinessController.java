package com.learn.healthcheckactuator.health.api;
import com.learn.healthcheckactuator.health.indicator.ElasticHealthIndicator;
import com.learn.healthcheckactuator.health.indicator.JmsHealthIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;



@Component
@Endpoint(id = "readiness")
@Slf4j
class ReadinessController {

    private ElasticHealthIndicator elasticHealthIndicator;
    private JmsHealthIndicator jmsHealthIndicator;

    /**
     * The init constructor get the indicators classes (Jms and ES) to operate the readiness method of them
     * @param elasticHealthIndicator  @param jmsHealthIndicator
     */
    @Autowired
    public ReadinessController(ElasticHealthIndicator elasticHealthIndicator, JmsHealthIndicator jmsHealthIndicator) {
        this.elasticHealthIndicator = elasticHealthIndicator;
        this.jmsHealthIndicator = jmsHealthIndicator;
    }

    /**
     * The method check the readiness of JMS and Elastic-Search as part of Health Check
     * @return the readiness status
     */
    @ReadOperation
    public String checkReadiness()  {
        try {
            elasticHealthIndicator.checkElasticSearchReadiness();
            jmsHealthIndicator.jmsHealthCheck();
            return "Health Check Readiness has been succeed";
        } catch (Exception e) {
            log.error("Health Check Readiness has been failed",e);
            throw e;
        }
    }


}