package fr.jg.account.domain.estate.tradingAccount;

import java.math.BigDecimal;

public class TradingTransaction extends Transaction {

    private Stock stock;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal fees;

    private TradingOperationEnum tradingOperationEnum;

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

    public TradingOperationEnum getTradingOperationEnum() {
        return this.tradingOperationEnum;
    }

    public void setTradingOperationEnum(final TradingOperationEnum tradingOperationEnum) {
        this.tradingOperationEnum = tradingOperationEnum;
    }
}
