package fr.jg.account.dao;

import org.springframework.hateoas.EntityModel;

import java.util.Currency;
import java.util.UUID;

public class AccountDao extends EntityModel<AccountDao> {
    private UUID id;

    private String name;

    private Currency currency;

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
}
