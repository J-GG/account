package fr.jg.account.ports.primary.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.CashAccount;
import fr.jg.account.domain.estate.cashAccount.CashTransaction;

import java.util.List;
import java.util.UUID;

public interface CashAccountBusiness {
    CashAccount create(CashAccount cashAccount);

    CashTransaction addTransactionToAccount(UUID cashAccountId, CashTransaction cashTransaction);

    List<CashAccount> getAll();

    CashAccount get(UUID cashAccountId);

    void delete(UUID cashAccountId);
}
