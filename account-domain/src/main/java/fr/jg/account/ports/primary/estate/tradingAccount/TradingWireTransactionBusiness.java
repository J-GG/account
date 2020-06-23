package fr.jg.account.ports.primary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;

import java.util.List;
import java.util.UUID;

public interface TradingWireTransactionBusiness {
    TradingWireTransaction create(TradingWireTransaction tradingWireTransaction);

    List<TradingWireTransaction> getAll();

    TradingWireTransaction get(UUID tradingWireTransactionId);

    List<TradingWireTransaction> getByTradingAccountId(UUID id);

    void delete(UUID tradingWireTransactionId);
}
