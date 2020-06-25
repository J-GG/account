package fr.jg.account.domain.estate;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public abstract class BaseEstate {

    protected UUID id;

    protected Estate estate;

    protected String name;

    protected Currency currency;

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Estate getEstate() {
        return this.estate;
    }

    public void setEstate(final Estate estate) {
        this.estate = estate;
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

    public abstract BigDecimal getBalance();
}
