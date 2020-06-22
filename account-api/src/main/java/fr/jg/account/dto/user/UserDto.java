package fr.jg.account.dto.user;

import fr.jg.account.dto.LinkedResource;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "users")
public class UserDto extends RepresentationModel<UserDto> {
    private UUID id;

    private String name;

    private LinkedResource<UUID> estate;

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
}
