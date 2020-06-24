package fr.jg.account.dto.estate.tradingAccount.financialmodeling;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialModelingDividend {

    private LocalDate date;

    private BigDecimal adjDividend;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAdjDividend() {
        return this.adjDividend;
    }

    public void setAdjDividend(final BigDecimal adjDividend) {
        this.adjDividend = adjDividend;
    }
}
