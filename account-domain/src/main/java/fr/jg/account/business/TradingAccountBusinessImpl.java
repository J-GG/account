package fr.jg.account.business;

import fr.jg.account.domain.TradingAccount;
import fr.jg.account.ports.primary.TradingAccountBusiness;
import fr.jg.account.ports.secondary.TradingAccountService;

import java.util.List;
import java.util.UUID;

public class TradingAccountBusinessImpl implements TradingAccountBusiness {

    private final TradingAccountService tradingAccountService;

    public TradingAccountBusinessImpl(final TradingAccountService tradingAccountService) {
        this.tradingAccountService = tradingAccountService;
    }

    @Override
    public TradingAccount create(final TradingAccount tradingAccount) {
        return this.tradingAccountService.create(tradingAccount);
    }

    @Override
    public List<TradingAccount> getAll() {
        return this.tradingAccountService.getAll();
    }

    @Override
    public TradingAccount get(final UUID tradingAccountId) {
        return this.tradingAccountService.get(tradingAccountId);
    }

    @Override
    public void delete(final UUID tradingAccountId) {
        this.tradingAccountService.delete(tradingAccountId);
    }
}
