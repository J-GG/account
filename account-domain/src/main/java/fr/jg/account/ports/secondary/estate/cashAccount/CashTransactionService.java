package fr.jg.account.ports.secondary.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.CashTransaction;

import java.util.List;
import java.util.UUID;

public interface CashTransactionService {
    CashTransaction create(CashTransaction cashTransaction);

    List<CashTransaction> getAll();

    List<CashTransaction> getByCashAccountId(UUID cashAccountId);

    CashTransaction get(UUID transactionId);

    void delete(UUID transactionId);
}
