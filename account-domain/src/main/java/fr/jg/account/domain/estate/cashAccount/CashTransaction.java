package fr.jg.account.domain.estate.cashAccount;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CashTransaction {

    private UUID id;

    private LocalDate date;

    private BigDecimal amount;

    private String comment;

    private CashAccount cashAccount;

    private Category category;

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

    public CashAccount getCashAccount() {
        return this.cashAccount;
    }

    public void setCashAccount(final CashAccount cashAccount) {
        this.cashAccount = cashAccount;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }
}
