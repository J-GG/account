package fr.jg.account.ports.primary;

import fr.jg.account.domain.CashAccount;
import fr.jg.account.domain.CashTransaction;

import java.util.List;
import java.util.UUID;

public interface CashAccountBusiness {
    CashAccount create(CashAccount cashAccount);

    CashTransaction addTransactionToAccount(UUID cashAccountId, CashTransaction cashTransaction);

    List<CashAccount> getAll();

    CashAccount get(UUID cashAccountId);

    void delete(UUID cashAccountId);
}
