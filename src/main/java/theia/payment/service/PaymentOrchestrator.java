package theia.payment.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import theia.payment.model.Account;
import theia.payment.model.PaymentRequest;
import theia.payment.model.PaymentResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class PaymentOrchestrator {

    private final AccountService accountService;

    public PaymentOrchestrator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Cacheable(cacheNames = "payments", key="#paymentRequest.requestId")
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        String sourceId = paymentRequest.sourceId();
        String targetId = paymentRequest.targetId();

        Account source = accountService.getAccountById(sourceId);
        Account target = accountService.getAccountById(targetId);
        if(source == null || target == null) {
            return null;
        }

        BigDecimal amount = paymentRequest.amount();
        accountService.addAmount(sourceId, amount.negate());
        accountService.addAmount(targetId, amount);

        String transactionId = UUID.randomUUID().toString();
        Instant timestamp = Instant.now();
        return new PaymentResponse(
                transactionId,
                sourceId,
                targetId,
                amount,
                paymentRequest.description(),
                timestamp);
    }
}
