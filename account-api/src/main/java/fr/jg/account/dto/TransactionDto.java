package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Relation(collectionRelation = "transactions")
public class TransactionDto extends RepresentationModel<TransactionDto> {
    private UUID id;

    private LocalDate date;

    private BigDecimal amount;

    private String comment;

    private LinkedResource<UUID> account;

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

    public LinkedResource<UUID> getAccount() {
        return account;
    }

    public void setAccount(final LinkedResource<UUID> account) {
        this.account = account;
    }

    public LinkedResource<Long> getCategory() {
        return category;
    }

    public void setCategory(final LinkedResource<Long> category) {
        this.category = category;
    }
}
