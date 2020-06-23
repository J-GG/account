package fr.jg.account.domain.estate.tradingAccount;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    private UUID id;

    private TradingAccount tradingAccount;

    private LocalDate date;

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

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }
}
