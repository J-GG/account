package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "users")
public class UserDto extends RepresentationModel<UserDto> {
    private UUID id;

    private String name;

    private LinkedResourceArray accounts;

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

    public LinkedResourceArray getAccounts() {
        return accounts;
    }

    public void setAccounts(final LinkedResourceArray accounts) {
        this.accounts = accounts;
    }
}
