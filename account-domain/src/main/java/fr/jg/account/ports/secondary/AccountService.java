package fr.jg.account.ports.secondary;

import fr.jg.account.domain.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account create(Account account);

    List<Account> getAll();

    Account get(UUID accountId);
}
