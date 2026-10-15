package com.fraudwatch.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions/reactive")
public class ReactiveTransactionController {

    private final TransactionService transactionService;

    public ReactiveTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public record ReactiveStatus(
        String status,
        String technology
    ) {}

    @GetMapping("/status")
    public Mono<ReactiveStatus> status() {
        return Mono.just(
            new ReactiveStatus("READY", "Spring WebFlux / Project Reactor")
        );
    }

    @PostMapping("/check")
    public Mono<TransactionController.TransactionResult> check(
            @RequestBody TransactionController.TransactionRequest request) {

        return Mono.fromSupplier(
            () -> transactionService.screen(request.amount())
        );
    }
}
