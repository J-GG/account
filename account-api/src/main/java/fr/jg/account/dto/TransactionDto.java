package fr.jg.account.dto;

import org.springframework.hateoas.EntityModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionDto extends EntityModel<TransactionDto> {
    private UUID id;

    private LocalDate date;

    private BigDecimal amount;

    private String note;

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

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }
}
