package fr.jg.account.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class TransactionModel extends RepresentationModel<TransactionModel> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDate date;

    private Long amount;

    private String note;

    @ManyToOne(targetEntity = AccountModel.class)
    @JoinColumn(nullable = false)
    private AccountModel account;

    public UUID getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return BigDecimal.valueOf(this.amount).divide(BigDecimal.valueOf(Math.pow(10, this.account.getCurrency().getDefaultFractionDigits())));
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount.multiply(BigDecimal.valueOf(this.account.getCurrency().getDefaultFractionDigits())).longValue();
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }
}
