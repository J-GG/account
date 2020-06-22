package fr.jg.account.ports.secondary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingAccount;

import java.util.List;
import java.util.UUID;

public interface TradingAccountService {
    TradingAccount create(TradingAccount tradingAccount);

    TradingAccount update(TradingAccount tradingAccount);

    List<TradingAccount> getAll();

    TradingAccount get(UUID tradingAccountId);

    void delete(UUID tradingAccountId);
}
