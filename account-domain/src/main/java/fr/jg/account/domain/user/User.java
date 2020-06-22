package fr.jg.account.domain.user;

import fr.jg.account.domain.estate.Estate;

import java.util.UUID;

public class User {

    private UUID id;

    private String name;

    private Estate estate;

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

    public Estate getEstate() {
        return this.estate;
    }

    public void setEstate(final Estate estate) {
        this.estate = estate;
    }
}

