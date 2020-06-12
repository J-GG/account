package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;

public class LinkedResource<ID> extends RepresentationModel<LinkedResource<ID>> {

    private ID id;

    public LinkedResource() {
    }

    public LinkedResource(final ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(final ID id) {
        this.id = id;
    }
}
