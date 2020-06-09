package fr.jg.account.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public class Account {
    private UUID id;

    private String name;

    private Currency currency;

    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return this.transactions.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
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

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setAccount(this);
    }
}

