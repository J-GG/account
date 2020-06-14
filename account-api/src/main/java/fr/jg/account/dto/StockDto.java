package fr.jg.account.dto;

import fr.jg.account.configuration.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StockDto {

    private String name;

    private String code;

    private int quantity;

    private BigDecimal investedAmount;

    private List<StockHistoryDto> history;

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

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final int quantity) {
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

    public List<StockHistoryDto> getHistory() {
        return this.history;
    }

    public void setHistory(final List<StockHistoryDto> history) {
        this.history = history;
    }

    public static class StockHistoryDto {
        private LocalDate date;

        private BigDecimal price;

        public LocalDate getDate() {
            return this.date;
        }

        public void setDate(final LocalDate date) {
            this.date = date;
        }

        public BigDecimal getPrice() {
            return this.price;
        }

        public void setPrice(final BigDecimal price) {
            this.price = price;
        }
    }
}
