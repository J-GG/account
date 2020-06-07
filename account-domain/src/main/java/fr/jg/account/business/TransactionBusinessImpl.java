package fr.jg.account.business;

import fr.jg.account.domain.Transaction;
import fr.jg.account.ports.primary.TransactionBusiness;
import fr.jg.account.ports.secondary.TransactionService;

import java.util.List;
import java.util.UUID;

public class TransactionBusinessImpl implements TransactionBusiness {

    private final TransactionService transactionService;

    public TransactionBusinessImpl(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Transaction create(final Transaction transaction) {
        return this.transactionService.create(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return this.transactionService.getAll();
    }

    @Override
    public List<Transaction> getAllByAccountId(final UUID accountId) {
        return this.transactionService.getAllByAccountId(accountId);
    }

    @Override
    public Transaction get(final UUID transactionId) {
        return this.transactionService.get(transactionId);
    }
}
