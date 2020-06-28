package fr.jg.account.dto.estate.tradingAccount;

import fr.jg.account.configuration.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

public class PortfolioStockDto {

    private String id;

    private String name;

    private Integer quantity;

    private BigDecimal investedAmount;

    private SortedMap<LocalDate, BigDecimal> history;

    private BigDecimal currentValue;

    private BigDecimal dividend;

    private BigDecimal estimatedAnnualYield;

    public PortfolioStockDto() {
        this.investedAmount = BigDecimal.ZERO;
        this.history = new TreeMap<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
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

    public BigDecimal getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(final BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getDividend() {
        return this.dividend;
    }

    public void setDividend(final BigDecimal dividend) {
        this.dividend = dividend;
    }

    public BigDecimal getEstimatedAnnualYield() {
        return this.estimatedAnnualYield;
    }

    public void setEstimatedAnnualYield(final BigDecimal estimatedAnnualYield) {
        this.estimatedAnnualYield = estimatedAnnualYield;
    }
}
