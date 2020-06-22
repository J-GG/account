package fr.jg.account.ports.primary.estate;

import fr.jg.account.domain.estate.Estate;
import fr.jg.account.domain.estate.cashAccount.CashAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingAccount;

import java.util.List;
import java.util.UUID;

public interface EstateBusiness {
    Estate create(Estate estate);

    Estate getByUserId(UUID userId);

    List<Estate> getAll();

    Estate get(UUID estateId);

    void delete(UUID estateId);

    CashAccount addCashAccountToEstate(CashAccount cashAccount, UUID estateId);

    TradingAccount addTradingAccountToEstate(TradingAccount tradingAccount, UUID estateId);
}
