package fr.jg.account.ports.secondary;

import fr.jg.account.domain.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction create(Transaction transaction);

    List<Transaction> getAll();

    List<Transaction> getByAccountId(UUID id);

    Transaction get(UUID transactionId);

    void delete(UUID transactionId);
}
