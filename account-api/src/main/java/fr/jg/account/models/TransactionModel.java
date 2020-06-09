package fr.jg.account.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class TransactionModel extends RepresentationModel<TransactionModel> {
    @Id
    @GeneratedValue(generator = "UUID")
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

    public void setId(final UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public AccountModel getAccount() {
        return this.account;
    }

    public void setAccount(final AccountModel account) {
        this.account = account;
    }
}
