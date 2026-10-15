package com.fraudwatch.backend;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private final TransactionService service = new TransactionService();

    @Test
    void flagsLargeTransactions() {
        var result = service.screen(new BigDecimal("4500"));
        assertEquals("FLAGGED", result.status());
    }

    @Test
    void clearsSmallTransactions() {
        var result = service.screen(new BigDecimal("150"));
        assertEquals("CLEAR", result.status());
    }

    @Test
    void rejectsNegativeAmounts() {
        var result = service.screen(new BigDecimal("-50"));
        assertEquals("INVALID", result.status());
    }

    @Test
    void checksThresholdBoundary() {
        assertEquals("CLEAR",
            service.screen(new BigDecimal("3000")).status());

        assertEquals("FLAGGED",
            service.screen(new BigDecimal("3000.01")).status());
    }
}
