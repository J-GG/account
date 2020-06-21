package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Currency;
import java.util.UUID;

public class BaseEstateDto<D extends RepresentationModel<? extends D>> extends RepresentationModel<D> {

    private UUID id;

    private String name;

    private LinkedResource<UUID> estate;

    private Currency currency;

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

    public LinkedResource<UUID> getEstate() {
        return this.estate;
    }

    public void setEstate(final LinkedResource<UUID> estate) {
        this.estate = estate;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }
}
