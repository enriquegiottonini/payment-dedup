package theia.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theia.payment.model.Account;
import theia.payment.service.AccountService;

@RestController
@RequestMapping("/api/account-details")
public class AccountDetailsController {

    private final AccountService accountService;

    public AccountDetailsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(
            description = "Retrieves the details of an account",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "Account not found")
            })
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountDetails(
            @Parameter(description = "ID of the account to retrieve", required = true)
            @PathVariable String accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }
}
