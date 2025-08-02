package theia.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theia.payment.model.Account;
import theia.payment.repository.AccountRepository;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(String accountId) {
        return accountRepository.getAccount(accountId);
    }

    public Account addAmount(String accountId, BigDecimal amount) {
        if (accountRepository.exists(accountId)) {
            Account account = accountRepository.getAccount(accountId);
            BigDecimal newBalance = account.total().add(amount);
            accountRepository.upsert(accountId, newBalance);
        }
        return accountRepository.getAccount(accountId);
    }
}
