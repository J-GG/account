package fr.jg.account.business.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingTransactionBusiness;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingTransactionService;

import java.util.List;
import java.util.UUID;

public class TradingTransactionBusinessImpl implements TradingTransactionBusiness {

    private final TradingTransactionService tradingTransactionService;

    public TradingTransactionBusinessImpl(final TradingTransactionService tradingTransactionService) {
        this.tradingTransactionService = tradingTransactionService;
    }

    @Override
    public TradingTransaction create(final TradingTransaction tradingTransaction) {
        return this.tradingTransactionService.create(tradingTransaction);
    }

    @Override
    public List<TradingTransaction> getAll() {
        return this.tradingTransactionService.getAll();
    }

    @Override
    public TradingTransaction get(final UUID tradingTransactionId) {
        return this.tradingTransactionService.get(tradingTransactionId);
    }

    @Override
    public List<TradingTransaction> getByTradingAccountId(final UUID id) {
        return this.tradingTransactionService.getByTradingAccountId(id);
    }

    @Override
    public void delete(final UUID tradingTransactionId) {
        this.tradingTransactionService.delete(tradingTransactionId);
    }
}
