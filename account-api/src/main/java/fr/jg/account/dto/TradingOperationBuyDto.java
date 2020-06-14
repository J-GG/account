package fr.jg.account.dto;

import java.math.BigDecimal;

public class TradingOperationBuyDto extends TradingOperationDto {

    private String code;

    private BigDecimal unitPrice;

    private int quantity;

    private BigDecimal fees;

    private String comment;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getFees() {
        return this.fees;
    }

    public void setFees(final BigDecimal fees) {
        this.fees = fees;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

}
