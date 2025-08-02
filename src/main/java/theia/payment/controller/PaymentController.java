package theia.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theia.payment.model.PaymentRequest;
import theia.payment.model.PaymentResponse;
import theia.payment.service.PaymentOrchestrator;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentOrchestrator paymentOrchestrator;

    public PaymentController(PaymentOrchestrator paymentOrchestrator) {
        this.paymentOrchestrator = paymentOrchestrator;
    }

    @Operation(
            summary = "Creates a new payment",
            description = "Process a payment request",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment processed successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request payment")})
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        PaymentResponse paymentResponse = paymentOrchestrator.createPayment(request);
        if (paymentResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(paymentResponse);
    }
}
