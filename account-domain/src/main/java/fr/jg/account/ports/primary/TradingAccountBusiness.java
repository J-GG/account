package fr.jg.account.ports.primary;

import fr.jg.account.domain.TradingAccount;

import java.util.List;
import java.util.UUID;

public interface TradingAccountBusiness {
    TradingAccount create(TradingAccount tradingAccount);

    List<TradingAccount> getAll();

    TradingAccount get(UUID tradingAccountId);

    void delete(UUID tradingAccountId);
}
