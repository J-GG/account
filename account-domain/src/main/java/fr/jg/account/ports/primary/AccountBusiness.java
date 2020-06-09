package fr.jg.account.ports.primary;

import fr.jg.account.domain.Account;
import fr.jg.account.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface AccountBusiness {
    Account create(Account account);

    Account addTransactionToAccount(UUID accountId, Transaction transaction);

    List<Account> getAll();

    Account get(UUID accountId);

    void delete(UUID accountId);
}
