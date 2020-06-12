package fr.jg.account.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;

    private String name;

    private List<Account> accounts;

    public User() {
        this.accounts = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(final Account account) {
        accounts.add(account);
        account.setUser(this);
    }
}

