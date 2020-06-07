package fr.jg.account.ports.primary;

import fr.jg.account.domain.Account;

import java.util.List;
import java.util.UUID;

public interface AccountBusiness {
    Account create(Account account);

    List<Account> getAll();

    Account get(UUID accountId);
}
