package fr.jg.account.ports.secondary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;

import java.util.List;
import java.util.UUID;

public interface TradingWireTransactionService {
    TradingWireTransaction create(TradingWireTransaction tradingWireTransaction);

    TradingWireTransaction update(TradingWireTransaction tradingWireTransaction);

    List<TradingWireTransaction> getAll();

    TradingWireTransaction get(UUID tradingWireTransactionId);

    List<TradingWireTransaction> getByTradingAccountId(final UUID tradingAccountId);

    void delete(UUID tradingWireTransactionId);
}
