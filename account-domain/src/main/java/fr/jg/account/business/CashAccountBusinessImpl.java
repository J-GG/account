package fr.jg.account.business;

import fr.jg.account.domain.CashAccount;
import fr.jg.account.domain.CashTransaction;
import fr.jg.account.ports.primary.CashAccountBusiness;
import fr.jg.account.ports.primary.CashTransactionBusiness;
import fr.jg.account.ports.secondary.CashAccountService;

import java.util.List;
import java.util.UUID;

public class CashAccountBusinessImpl implements CashAccountBusiness {

    private final CashAccountService cashAccountService;

    private final CashTransactionBusiness cashTransactionBusiness;


    public CashAccountBusinessImpl(final CashAccountService cashAccountService, final CashTransactionBusiness cashTransactionBusiness) {
        this.cashAccountService = cashAccountService;
        this.cashTransactionBusiness = cashTransactionBusiness;
    }

    @Override
    public CashAccount create(final CashAccount cashAccount) {
        return this.cashAccountService.create(cashAccount);
    }

    @Override
    public CashTransaction addTransactionToAccount(final UUID accountId, final CashTransaction cashTransaction) {
        final CashAccount cashAccount = this.cashAccountService.get(accountId);
        cashTransaction.setCashAccount(cashAccount);

        return this.cashTransactionBusiness.create(cashTransaction);
    }

    @Override
    public List<CashAccount> getAll() {
        return this.cashAccountService.getAll();
    }

    @Override
    public CashAccount get(final UUID accountId) {
        return this.cashAccountService.get(accountId);
    }

    @Override
    public void delete(final UUID accountId) {
        this.cashAccountService.delete(accountId);
    }
}
