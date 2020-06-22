package fr.jg.account.business.estate;

import fr.jg.account.domain.estate.Estate;
import fr.jg.account.domain.estate.cashAccount.CashAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.ports.primary.estate.EstateBusiness;
import fr.jg.account.ports.primary.estate.cashAccount.CashAccountBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingAccountBusiness;
import fr.jg.account.ports.secondary.estate.EstateService;

import java.util.List;
import java.util.UUID;

public class EstateBusinessImpl implements EstateBusiness {

    private final EstateService estateService;

    private final CashAccountBusiness cashAccountBusiness;

    private final TradingAccountBusiness tradingAccountBusiness;

    public EstateBusinessImpl(final EstateService estateService, final CashAccountBusiness cashAccountBusiness, final TradingAccountBusiness tradingAccountBusiness) {
        this.estateService = estateService;
        this.cashAccountBusiness = cashAccountBusiness;
        this.tradingAccountBusiness = tradingAccountBusiness;
    }

    @Override
    public Estate create(final Estate estate) {
        return this.estateService.create(estate);
    }

    @Override
    public Estate getByUserId(final UUID userId) {
        return this.estateService.getByUserId(userId);
    }

    @Override
    public List<Estate> getAll() {
        return this.estateService.getAll();
    }

    @Override
    public Estate get(final UUID estateId) {
        return this.estateService.get(estateId);
    }

    @Override
    public void delete(final UUID estateId) {
        this.estateService.delete(estateId);
    }

    @Override
    public CashAccount addCashAccountToEstate(final CashAccount cashAccount, final UUID estateId) {
        final Estate estate = this.get(estateId);
        cashAccount.setEstate(estate);

        return this.cashAccountBusiness.create(cashAccount);
    }

    @Override
    public TradingAccount addTradingAccountToEstate(final TradingAccount tradingAccount, final UUID estateId) {
        final Estate estate = this.get(estateId);
        tradingAccount.setEstate(estate);

        return this.tradingAccountBusiness.create(tradingAccount);
    }
}
