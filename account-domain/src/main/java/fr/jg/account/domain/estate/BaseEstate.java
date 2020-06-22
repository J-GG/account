package fr.jg.account.domain.estate;

import java.util.Currency;
import java.util.UUID;

public class BaseEstate {

    private UUID id;

    private Estate estate;

    private String name;

    private Currency currency;

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
}
