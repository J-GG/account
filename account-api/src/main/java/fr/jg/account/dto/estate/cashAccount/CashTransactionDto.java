package fr.jg.account.dto.estate.cashAccount;

import fr.jg.account.dto.LinkedResource;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Relation(collectionRelation = "cashTransactions")
public class CashTransactionDto extends RepresentationModel<CashTransactionDto> {

    private UUID id;

    private LocalDate date;

    private BigDecimal amount;

    private String comment;

    private LinkedResource<UUID> cashAccount;

    private LinkedResource<Long> category;

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

    public LinkedResource<UUID> getCashAccount() {
        return this.cashAccount;
    }

    public void setCashAccount(final LinkedResource<UUID> cashAccount) {
        this.cashAccount = cashAccount;
    }

    public LinkedResource<Long> getCategory() {
        return this.category;
    }

    public void setCategory(final LinkedResource<Long> category) {
        this.category = category;
    }
}
