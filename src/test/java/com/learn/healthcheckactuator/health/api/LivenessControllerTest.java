package com.learn.healthcheckactuator.health.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;


public class LivenessControllerTest {

    private LivenessController sut = new LivenessController();


    /**
     * The test check the liveness of the application and verify it via assertion of the result
     */
    @Test
    public void testCheckLiveness() {
        String result = sut.checkLiveness();
        Assertions.assertEquals(sut.getLIVENESS_SUCCEED(), result);
    }

}