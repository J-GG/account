package fr.jg.account.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(nullable = false)
    private UserModel user;

    private String name;

    private Currency currency;

    private Long yieldRate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TransactionModel> transactions;

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(final UserModel user) {
        this.user = user;
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

    public Long getYieldRate() {
        return this.yieldRate;
    }

    public void setYieldRate(final Long yieldRate) {
        this.yieldRate = yieldRate;
    }

    public List<TransactionModel> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TransactionModel> transactions) {
        this.transactions = transactions;
    }
}
