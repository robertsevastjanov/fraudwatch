package com.fraudwatch.backend;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

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

        if (transaction.amount() == null ||
            transaction.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest()
                .body(new TransactionResult("INVALID", "Amount must be positive"));
        }

        boolean flagged =
            transaction.amount().compareTo(new BigDecimal("3000")) > 0;

        return ResponseEntity.ok(new TransactionResult(
            flagged ? "FLAGGED" : "CLEAR",
            flagged ? "Amount exceeds 3000 EUR threshold" : "No rule triggered"
        ));
    }
}
