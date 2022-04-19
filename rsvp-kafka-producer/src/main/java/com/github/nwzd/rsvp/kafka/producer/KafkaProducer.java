package com.github.nwzd.rsvp.kafka.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;

@Component
@EnableBinding(Source.class)
public class KafkaProducer {
    private static final int TIMEOUT_MS = 10000;

    private final Source source;

    public KafkaProducer(Source source) {
        this.source = source;
    }

    public void sendMessage(WebSocketMessage<?> message) {
        source.output().send(MessageBuilder.withPayload(message.getPayload()).build(), TIMEOUT_MS);
    }
}
