package fr.jg.account.ports.secondary;

import fr.jg.account.domain.CashTransaction;

import java.util.List;
import java.util.UUID;

public interface CashTransactionService {
    CashTransaction create(CashTransaction cashTransaction);

    List<CashTransaction> getAll();

    List<CashTransaction> getByCashAccountId(UUID cashAccountId);

    CashTransaction get(UUID transactionId);

    void delete(UUID transactionId);
}
