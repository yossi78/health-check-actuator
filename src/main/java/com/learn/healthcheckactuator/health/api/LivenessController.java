package com.learn.healthcheckactuator.health.api;
import com.redbend.telemery.health.exception.LivenessFailureException;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;




@Component
@Endpoint(id = "liveness")
@Slf4j
@Data
public class LivenessController {

    private String LIVENESS_SUCCEED="Health Check liveness has been succeed";
    private String LIVENESS_FAILED="Health Check liveness has been failed";


    /** Health check liveness method verify application liveness via API call
     * @return status of application */

    @SneakyThrows
    @ReadOperation
    public String checkLiveness() {
        try {
            log.debug(LIVENESS_SUCCEED);
            return LIVENESS_SUCCEED;
        } catch (Exception e) {
            log.error(LIVENESS_FAILED, e);
            throw new LivenessFailureException(LIVENESS_FAILED);
        }
    }


}