package fr.jg.account.domain.estate.tradingAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TradingWireTransaction {

    private UUID id;

    private TradingAccount tradingAccount;

    private LocalDate date;

    private BigDecimal amount;

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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }
}
