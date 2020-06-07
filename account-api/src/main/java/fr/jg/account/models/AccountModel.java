package fr.jg.account.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
public class AccountModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private Currency currency;

    @OneToMany(mappedBy = "account")
    private List<TransactionModel> transactions;

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public List<TransactionModel> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TransactionModel> transactions) {
        this.transactions = transactions;
    }
}
