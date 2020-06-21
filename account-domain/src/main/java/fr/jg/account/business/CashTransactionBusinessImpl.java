package fr.jg.account.business;

import fr.jg.account.domain.CashTransaction;
import fr.jg.account.ports.primary.CashTransactionBusiness;
import fr.jg.account.ports.secondary.CashTransactionService;

import java.util.List;
import java.util.UUID;

public class CashTransactionBusinessImpl implements CashTransactionBusiness {

    private final CashTransactionService cashTransactionService;

    public CashTransactionBusinessImpl(final CashTransactionService cashTransactionService) {
        this.cashTransactionService = cashTransactionService;
    }

    @Override
    public CashTransaction create(final CashTransaction cashTransaction) {
        return this.cashTransactionService.create(cashTransaction);
    }

    @Override
    public List<CashTransaction> getAll() {
        return this.cashTransactionService.getAll();
    }

    @Override
    public List<CashTransaction> getByCashAccountId(final UUID cashAccountId) {
        return this.cashTransactionService.getByCashAccountId(cashAccountId);
    }

    @Override
    public CashTransaction get(final UUID transactionId) {
        return this.cashTransactionService.get(transactionId);
    }

    @Override
    public void delete(final UUID transactionId) {
        this.cashTransactionService.delete(transactionId);
    }
}
