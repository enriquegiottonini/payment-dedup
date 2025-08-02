package theia.payment.model;

import java.math.BigDecimal;

public record PaymentRequest(
        String sourceId,
        String targetId,
        BigDecimal amount,
        String description
) {}