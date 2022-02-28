package com.learn.healthcheckactuator.health.indicator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class JmsHealthIndicatorTest {

    @Mock
    private ConnectionFactory connectionFactory;

    @InjectMocks
    private JmsHealthIndicator jmsHealthCheckAspect = new JmsHealthIndicator(connectionFactory);

    /**
     * The Test check the Jms health indicator and verify it via JmsException handling
     *
     * @throws JMSException
     */
    @Test
    public void throws_ApplicationException_when_JmsException_occurs() {
        Assertions.assertThrows(Exception.class, () -> {
            when(connectionFactory.createConnection()).thenThrow(new JMSException("Test exception"));
            jmsHealthCheckAspect.jmsHealthCheck();
        });
    }

}
