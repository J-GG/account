package fr.jg.account.dto.estate.tradingAccount;

import java.math.BigDecimal;

public class TradingOperationBuyDto extends TradingOperationDto {

    private String code;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal fees;

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
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
}
