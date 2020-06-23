package fr.jg.account.business.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingWireTransactionBusiness;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingWireTransactionService;

import java.util.List;
import java.util.UUID;

public class TradingWireTransactionBusinessImpl implements TradingWireTransactionBusiness {

    private final TradingWireTransactionService tradingWireTransactionService;

    public TradingWireTransactionBusinessImpl(final TradingWireTransactionService tradingWireTransactionService) {
        this.tradingWireTransactionService = tradingWireTransactionService;
    }

    @Override
    public TradingWireTransaction create(final TradingWireTransaction tradingWireTransaction) {
        return this.tradingWireTransactionService.create(tradingWireTransaction);
    }

    @Override
    public List<TradingWireTransaction> getAll() {
        return this.tradingWireTransactionService.getAll();
    }

    @Override
    public TradingWireTransaction get(final UUID tradingWireTransactionId) {
        return this.tradingWireTransactionService.get(tradingWireTransactionId);
    }

    @Override
    public List<TradingWireTransaction> getByTradingAccountId(final UUID id) {
        return this.tradingWireTransactionService.getByTradingAccountId(id);
    }

    @Override
    public void delete(final UUID tradingWireTransactionId) {
        this.tradingWireTransactionService.delete(tradingWireTransactionId);
    }
}
