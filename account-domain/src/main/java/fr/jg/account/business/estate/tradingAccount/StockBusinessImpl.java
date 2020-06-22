package fr.jg.account.business.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.Stock;
import fr.jg.account.ports.primary.estate.tradingAccount.StockBusiness;
import fr.jg.account.ports.secondary.estate.tradingAccount.StockService;

import java.util.List;

public class StockBusinessImpl implements StockBusiness {

    private final StockService stockService;

    public StockBusinessImpl(final StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public Stock get(final String companyCode) {
        return this.stockService.get(companyCode);
    }

    @Override
    public List<Stock> getAll() {
        return this.stockService.getAll();
    }
}
