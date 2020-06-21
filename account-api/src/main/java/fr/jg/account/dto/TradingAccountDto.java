package fr.jg.account.dto;

import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Relation(collectionRelation = "tradingAccounts")
public class TradingAccountDto extends BaseEstateDto<CashAccountDto> {

    private List<TransactionDto> transactions;

    private Map<String, StockDto> stocks;

    private BigDecimal investedAmount;

    private BigDecimal cashValue;


    public TradingAccountDto() {
        this.investedAmount = BigDecimal.ZERO;
        this.cashValue = BigDecimal.ZERO;
    }

    public List<TransactionDto> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public Map<String, StockDto> getStocks() {
        return this.stocks;
    }

    public void setStocks(final Map<String, StockDto> stocks) {
        this.stocks = stocks;
    }

    public BigDecimal getInvestedAmount() {
        return this.investedAmount;
    }

    public void setInvestedAmount(final BigDecimal investedAmount) {
        this.investedAmount = investedAmount;
    }

    public BigDecimal getCashValue() {
        return this.cashValue;
    }

    public void setCashValue(final BigDecimal cashValue) {
        this.cashValue = cashValue;
    }

    public BigDecimal getStockValue() {
        return this.stocks.values().stream().map(StockDto::getCurrentValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getCurrentValue() {
        return this.cashValue.add(this.getStockValue());
    }

    public static class TransactionDto {

        private UUID id;

        private LocalDate date;

        private TradingOperationDto operation;

        public UUID getId() {
            return this.id;
        }

        public void setId(final UUID id) {
            this.id = id;
        }

        public LocalDate getDate() {
            return this.date;
        }

        public void setDate(final LocalDate date) {
            this.date = date;
        }

        public TradingOperationDto getOperation() {
            return this.operation;
        }

        public void setOperation(final TradingOperationDto operation) {
            this.operation = operation;
        }
    }
}
