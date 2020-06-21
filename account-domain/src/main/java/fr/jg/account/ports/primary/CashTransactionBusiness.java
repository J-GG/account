package fr.jg.account.ports.primary;

import fr.jg.account.domain.CashTransaction;

import java.util.List;
import java.util.UUID;

public interface CashTransactionBusiness {

    CashTransaction create(final CashTransaction cashTransaction);

    List<CashTransaction> getAll();

    List<CashTransaction> getByCashAccountId(UUID id);

    CashTransaction get(UUID transactionId);

    void delete(UUID transactionId);
}
