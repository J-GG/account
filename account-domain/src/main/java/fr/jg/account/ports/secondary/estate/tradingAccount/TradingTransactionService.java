package fr.jg.account.ports.secondary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;

import java.util.List;
import java.util.UUID;

public interface TradingTransactionService {
    TradingTransaction create(TradingTransaction tradingTransaction);

    TradingTransaction update(TradingTransaction tradingTransaction);

    List<TradingTransaction> getAll();

    TradingTransaction get(UUID tradingTransactionId);

    List<TradingTransaction> getByTradingAccountId(UUID tradingAccountId);

    void delete(UUID tradingTransactionId);
}
