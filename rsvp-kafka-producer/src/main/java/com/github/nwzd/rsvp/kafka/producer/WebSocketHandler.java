package com.github.nwzd.rsvp.kafka.producer;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger LOGGER = Logger.getLogger(WebSocketHandler.class.getName());

    private final KafkaProducer kafkaProducer;

    public WebSocketHandler(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        LOGGER.log(Level.INFO, (String) message.getPayload());
        kafkaProducer.sendMessage(message);
    }
}
