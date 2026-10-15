package com.fraudwatch.backend;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public record TransactionRequest(
        String sender,
        String receiver,
        BigDecimal amount
    ) {}

    public record TransactionResult(
        String status,
        String reason
    ) {}

    @PostMapping("/check")
    public ResponseEntity<TransactionResult> check(
            @RequestBody TransactionRequest transaction) {

        TransactionResult result =
            transactionService.screen(transaction.amount());

        if ("INVALID".equals(result.status())) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }
}
