package com.github.nwzd.rsvp.kafka.main;

import com.github.nwzd.rsvp.kafka.producer.WebSocketHandler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootApplication
@ComponentScan({"com.github.nwzd.rsvp"})
public class App {
    private static final String END_POINT = "wss://ws.coincap.io/trades/binance";

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @Bean
    public ApplicationRunner initConnection(WebSocketHandler webSocketHandler) {
        return args -> {
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            webSocketClient.doHandshake(webSocketHandler, END_POINT);
        };
    }
}
