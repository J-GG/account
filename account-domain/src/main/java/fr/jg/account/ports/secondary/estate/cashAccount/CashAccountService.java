package fr.jg.account.ports.secondary.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.CashAccount;

import java.util.List;
import java.util.UUID;

public interface CashAccountService {
    CashAccount create(CashAccount cashAccount);

    CashAccount update(CashAccount cashAccount);

    List<CashAccount> getAll();

    CashAccount get(UUID accountId);

    void delete(UUID accountId);
}
