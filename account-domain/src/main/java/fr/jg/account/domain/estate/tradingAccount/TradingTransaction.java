package fr.jg.account.domain.estate.tradingAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TradingTransaction {

    private UUID id;

    private TradingAccount tradingAccount;

    private LocalDate date;

    private String comment;

    private Stock stock;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal fees;

    private TradingOperationEnum operation;

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public TradingAccount getTradingAccount() {
        return this.tradingAccount;
    }

    public void setTradingAccount(final TradingAccount tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(final Stock stock) {
        this.stock = stock;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getFees() {
        return this.fees;
    }

    public void setFees(final BigDecimal fees) {
        this.fees = fees;
    }

    public TradingOperationEnum getOperation() {
        return this.operation;
    }

    public void setOperation(final TradingOperationEnum operation) {
        this.operation = operation;
    }
}
