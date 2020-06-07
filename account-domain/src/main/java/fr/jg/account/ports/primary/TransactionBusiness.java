package fr.jg.account.ports.primary;

import fr.jg.account.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionBusiness {
    Transaction create(Transaction transaction);

    List<Transaction> getAll();

    List<Transaction> getAllByAccountId(UUID id);

    Transaction get(UUID transactionId);
}
