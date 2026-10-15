package com.fraudwatch.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration,org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration"
    }
)
class ReactiveTransactionControllerTest {

    @LocalServerPort
    int port;

    @Test
    void flagsLargeTransaction() {
        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();

        TransactionController.TransactionResult result = client.post()
                .uri("/api/transactions/reactive/check")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of(
                    "sender", "Alice",
                    "receiver", "Bob",
                    "amount", 4500
                ))
                .retrieve()
                .body(TransactionController.TransactionResult.class);

        assertEquals("FLAGGED", result.status());
    }
}
