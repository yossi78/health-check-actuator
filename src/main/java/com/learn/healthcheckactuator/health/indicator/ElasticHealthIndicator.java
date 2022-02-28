package com.learn.healthcheckactuator.health.indicator;
import com.learn.healthcheckactuator.properties.ElasticSearchProperties;
import com.redbend.telemery.health.exception.ElasticSearchClusterNotReadyException;
import com.redbend.telemery.health.dto.ElasticSearchClusterHealth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ElasticHealthIndicator {


    private ElasticSearchProperties elasticSearchProperties;
    private String ELASTIC_SEARCH_READINESS_SUCEED = "Health Check Readiness of Elastic Search passed successfully";
    private String ELASTIC_SEARCH_READINESS_FAILED = "Health Check Readiness of Elastic Search has been failed";

    /**
     * The init constructor get the Elastic Search properties for getting its keys
     *
     * @param elasticSearchProperties
     */
    @Autowired
    public ElasticHealthIndicator(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }

    /**
     * ElasticHealthIndicator check the readiness of the elastic search cluster node accept "green" and "yellow" statuses
     * in case of "red" of other failure the app readiness will be failed
     */
    public void checkElasticSearchReadiness() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ElasticSearchClusterHealth elasticSearchClusterHealth = restTemplate.getForObject
                    (elasticSearchProperties.getClusterNodes().get(0) + "/_cluster/health/", ElasticSearchClusterHealth.class);
            String status = elasticSearchClusterHealth.getStatus();
            if (status.equals("red")) {
                throw new ElasticSearchClusterNotReadyException(ELASTIC_SEARCH_READINESS_FAILED);
            }
            log.debug(ELASTIC_SEARCH_READINESS_SUCEED);
        } catch (Exception e) {
            log.error(ELASTIC_SEARCH_READINESS_FAILED);
            throw new ElasticSearchClusterNotReadyException(ELASTIC_SEARCH_READINESS_FAILED);
        }
    }


}
