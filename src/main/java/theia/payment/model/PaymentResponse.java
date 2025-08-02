package theia.payment.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponse(
        String paymentId,
        String sourceId,
        String targetId,
        BigDecimal amount,
        String description,
        Instant timestamp
) implements Serializable {}
