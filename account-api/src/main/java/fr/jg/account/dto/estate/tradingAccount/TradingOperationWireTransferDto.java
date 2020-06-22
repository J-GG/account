package fr.jg.account.dto.estate.tradingAccount;

import java.math.BigDecimal;

public class TradingOperationWireTransferDto extends TradingOperationDto {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
