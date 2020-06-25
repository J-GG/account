package fr.jg.account.domain.estate.tradingAccount;

import fr.jg.account.configuration.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

public class PortfolioStock {

    private String name;

    private String code;

    private Integer quantity;

    private BigDecimal investedAmount;

    private SortedMap<LocalDate, BigDecimal> history;

    public PortfolioStock() {
        this.investedAmount = BigDecimal.ZERO;
        this.history = new TreeMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return this.investedAmount.divide(BigDecimal.valueOf(this.quantity), 5, Configuration.ROUNDING_MODE);
    }

    public BigDecimal getInvestedAmount() {
        return this.investedAmount;
    }

    public void setInvestedAmount(final BigDecimal investedAmount) {
        this.investedAmount = investedAmount;
    }

    public SortedMap<LocalDate, BigDecimal> getHistory() {
        return this.history;
    }

    public void setHistory(final SortedMap<LocalDate, BigDecimal> history) {
        this.history = history;
    }

    public BigDecimal getCurrentPrice() {
        return this.history.values().toArray(new BigDecimal[] {})[this.history.size() - 1];
    }

    public BigDecimal getCurrentValue() {
        return getCurrentPrice().multiply(BigDecimal.valueOf(this.quantity));
    }
}
