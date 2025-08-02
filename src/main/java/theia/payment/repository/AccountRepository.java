package theia.payment.repository;

import org.springframework.stereotype.Repository;
import theia.payment.model.Account;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {

    private final ConcurrentHashMap<String, Account> accounts;

    public AccountRepository() {
        this.accounts = new ConcurrentHashMap<>();
        accounts.put("dionne", new Account("dionne", new BigDecimal("100.00")));
        accounts.put("enrique", new Account("enrique", new BigDecimal("100.00")));
    }

    public boolean exists(String accountId) {
        return accounts.containsKey(accountId);
    }

    public Account getAccount(String accountId) {
        return this.accounts.get(accountId);
    }

    public void upsert(String accountId, BigDecimal amount) {
        this.accounts.put(accountId, new Account(accountId, amount));
    }
}
