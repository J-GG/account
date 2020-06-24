package fr.jg.account.dto.estate.tradingAccount.financialmodeling;

import java.util.List;

public class FinancialModelingHistoricalDividendList {

    private List<FinancialModelingHistoricalDividend> historicalStockList;

    public List<FinancialModelingHistoricalDividend> getHistoricalStockList() {
        return this.historicalStockList;
    }

    public void setHistoricalStockList(final List<FinancialModelingHistoricalDividend> historicalStockList) {
        this.historicalStockList = historicalStockList;
    }
}
