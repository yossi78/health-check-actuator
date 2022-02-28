package com.learn.healthcheckactuator.properties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
public class ElasticSearchProperties {

    @Value("${telemetry.elastic-search.index.installation-duration}")
    private String installationDuration;


    @Value("${spring.elasticsearch.uris}")
    private List<String> clusterNodes;

}
