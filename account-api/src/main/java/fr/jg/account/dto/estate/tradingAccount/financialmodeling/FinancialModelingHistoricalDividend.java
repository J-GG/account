package fr.jg.account.dto.estate.tradingAccount.financialmodeling;

import java.util.List;

public class FinancialModelingHistoricalDividend {

    private String symbol;

    private List<FinancialModelingDividend> historical;

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public List<FinancialModelingDividend> getHistorical() {
        return this.historical;
    }

    public void setHistorical(final List<FinancialModelingDividend> historical) {
        this.historical = historical;
    }
}
