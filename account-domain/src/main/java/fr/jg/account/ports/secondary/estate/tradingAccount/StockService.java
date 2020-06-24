package fr.jg.account.ports.secondary.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.Stock;

import java.util.List;

public interface StockService {

    Stock create(Stock stock);

    Stock get(String companyCode);

    List<Stock> getAll();
}
