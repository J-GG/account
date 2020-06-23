package fr.jg.account.domain.estate.tradingAccount;

import java.math.BigDecimal;

public class TradingWireTransaction extends Transaction {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
