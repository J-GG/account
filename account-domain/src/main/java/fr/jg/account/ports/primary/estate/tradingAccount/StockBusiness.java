package fr.jg.account.ports.primary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.Stock;

import java.util.List;

public interface StockBusiness {

    Stock create(Stock stock);

    Stock get(String stockId);

    List<Stock> getAll();
}
