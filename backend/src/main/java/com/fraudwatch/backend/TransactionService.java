package com.fraudwatch.backend;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private static final BigDecimal THRESHOLD = new BigDecimal("3000");

    public TransactionController.TransactionResult screen(BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return new TransactionController.TransactionResult(
                "INVALID", "Amount must be positive"
            );
        }

        boolean flagged = amount.compareTo(THRESHOLD) > 0;

        return new TransactionController.TransactionResult(
            flagged ? "FLAGGED" : "CLEAR",
            flagged ? "Amount exceeds 3000 EUR threshold" : "No rule triggered"
        );
    }
}
