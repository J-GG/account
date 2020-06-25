package fr.jg.account.business.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.Stock;
import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.ports.primary.estate.tradingAccount.StockBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingAccountBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingTransactionBusiness;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingAccountService;

import java.util.List;
import java.util.UUID;

public class TradingAccountBusinessImpl implements TradingAccountBusiness {

    private final TradingAccountService tradingAccountService;

    private final TradingTransactionBusiness tradingTransactionBusiness;

    private final StockBusiness stockBusiness;

    public TradingAccountBusinessImpl(final TradingAccountService tradingAccountService, final TradingTransactionBusiness tradingTransactionBusiness, final StockBusiness stockBusiness) {
        this.tradingAccountService = tradingAccountService;
        this.tradingTransactionBusiness = tradingTransactionBusiness;
        this.stockBusiness = stockBusiness;
    }

    @Override
    public TradingAccount create(final TradingAccount tradingAccount) {
        return this.tradingAccountService.create(tradingAccount);
    }

    @Override
    public TradingTransaction addTransactionToAccount(final UUID tradingAccountId, final TradingTransaction tradingTransaction) {
        final TradingAccount tradingAccount = this.tradingAccountService.get(tradingAccountId);
        tradingTransaction.setTradingAccount(tradingAccount);

        final Stock stock = this.stockBusiness.get(tradingTransaction.getStock().getId());
        tradingTransaction.setStock(stock);

        return this.tradingTransactionBusiness.create(tradingTransaction);
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
