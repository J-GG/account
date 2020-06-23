package fr.jg.account.business.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingAccountBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingTransactionBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingWireTransactionBusiness;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingAccountService;

import java.util.List;
import java.util.UUID;

public class TradingAccountBusinessImpl implements TradingAccountBusiness {

    private final TradingAccountService tradingAccountService;

    private final TradingTransactionBusiness tradingTransactionBusiness;

    private final TradingWireTransactionBusiness tradingWireTransactionBusiness;

    public TradingAccountBusinessImpl(final TradingAccountService tradingAccountService, final TradingTransactionBusiness tradingTransactionBusiness, final TradingWireTransactionBusiness tradingWireTransactionBusiness) {
        this.tradingAccountService = tradingAccountService;
        this.tradingTransactionBusiness = tradingTransactionBusiness;
        this.tradingWireTransactionBusiness = tradingWireTransactionBusiness;
    }

    @Override
    public TradingAccount create(final TradingAccount tradingAccount) {
        return this.tradingAccountService.create(tradingAccount);
    }

    @Override
    public TradingTransaction addTransactionToAccount(final UUID tradingAccountId, final TradingTransaction tradingTransaction) {
        final TradingAccount tradingAccount = this.tradingAccountService.get(tradingAccountId);
        tradingTransaction.setTradingAccount(tradingAccount);

        return this.tradingTransactionBusiness.create(tradingTransaction);
    }

    @Override
    public TradingWireTransaction addWireTransactionToAccount(final UUID tradingAccountId, final TradingWireTransaction tradingWireTransaction) {
        final TradingAccount tradingAccount = this.tradingAccountService.get(tradingAccountId);
        tradingWireTransaction.setTradingAccount(tradingAccount);

        return this.tradingWireTransactionBusiness.create(tradingWireTransaction);
    }

    @Override
    public List<TradingAccount> getAll() {
        return this.tradingAccountService.getAll();
    }

    @Override
    public TradingAccount get(final UUID tradingAccountId) {
        return this.tradingAccountService.get(tradingAccountId);
    }

    @Override
    public void delete(final UUID tradingAccountId) {
        this.tradingAccountService.delete(tradingAccountId);
    }
}
