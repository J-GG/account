package fr.jg.account.domain.estate.tradingAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TradingTransaction {

    private UUID id;

    private TradingAccount tradingAccount;

    private Stock stock;

    private LocalDate date;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal fees;

    private TradingOperationEnum tradingOperationEnum;

    private String comment;

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

    public Stock getStock() {
        return this.stock;
    }

    public void setStock(final Stock stock) {
        this.stock = stock;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
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

    public TradingOperationEnum getTradingOperationEnum() {
        return this.tradingOperationEnum;
    }

    public void setTradingOperationEnum(final TradingOperationEnum tradingOperationEnum) {
        this.tradingOperationEnum = tradingOperationEnum;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }
}
