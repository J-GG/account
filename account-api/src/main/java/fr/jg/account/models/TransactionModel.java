package fr.jg.account.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDate date;

    private Long amount;

    private String comment;

    @ManyToOne(targetEntity = AccountModel.class)
    @JoinColumn(nullable = false)
    private AccountModel account;

    @ManyToOne(targetEntity = CategoryModel.class)
    private CategoryModel category;

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

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public AccountModel getAccount() {
        return this.account;
    }

    public void setAccount(final AccountModel account) {
        this.account = account;
    }

    public CategoryModel getCategory() {
        return this.category;
    }

    public void setCategory(final CategoryModel category) {
        this.category = category;
    }
}
