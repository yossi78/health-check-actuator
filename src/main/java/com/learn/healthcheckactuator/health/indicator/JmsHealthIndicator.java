package com.learn.healthcheckactuator.health.indicator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JmsHealthIndicator {

    private ConnectionFactory connectionFactory;

    /**
     * The init contsructor get the connecion factotry in order to create a connection in the readiness method
     * @param connectionFactory
     */
    @Autowired
    public JmsHealthIndicator(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * The method check the JMS health check by openning a connection to ActiveMQ
     */
    @SneakyThrows
    public void jmsHealthCheck() {
        try (Connection connection = connectionFactory.createConnection()) {
            connection.start();
        } catch (JMSException e) {
            log.error("Readiness of ActiveMQ has been failed", e);
            throw e;
        }
    }

}
