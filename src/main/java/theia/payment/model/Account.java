package theia.payment.model;

import java.math.BigDecimal;

public record Account(
        String accountId,
        BigDecimal total
) {}
