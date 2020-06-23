package fr.jg.account.ports.primary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingTransaction;
import fr.jg.account.domain.estate.tradingAccount.TradingWireTransaction;

import java.util.List;
import java.util.UUID;

public interface TradingAccountBusiness {
    TradingAccount create(TradingAccount tradingAccount);

    TradingTransaction addTransactionToAccount(UUID tradingAccountId, TradingTransaction tradingTransaction);

    TradingWireTransaction addWireTransactionToAccount(UUID tradingAccountId, TradingWireTransaction tradingWireTransaction);

    List<TradingAccount> getAll();

    TradingAccount get(UUID tradingAccountId);

    void delete(UUID tradingAccountId);
}
