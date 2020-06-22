package fr.jg.account.ports.primary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;

import java.util.List;
import java.util.UUID;

public interface TradingTransactionBusiness {
    TradingTransaction create(TradingTransaction tradingTransaction);

    List<TradingTransaction> getAll();

    TradingTransaction get(UUID tradingTransactionId);

    List<TradingTransaction> getByTradingAccountId(UUID id);

    void delete(UUID tradingTransactionId);
}
