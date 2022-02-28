package com.redbend.telemery.health.exception;


public class ElasticSearchClusterNotReadyException extends RuntimeException {

    public ElasticSearchClusterNotReadyException(String message){
        super(message);
    }

}
