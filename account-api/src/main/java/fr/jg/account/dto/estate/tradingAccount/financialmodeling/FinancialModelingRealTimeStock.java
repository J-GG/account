package fr.jg.account.dto.estate.tradingAccount.financialmodeling;

import java.math.BigDecimal;

public class FinancialModelingRealTimeStock {

    private String symbol;

    private BigDecimal price;

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
