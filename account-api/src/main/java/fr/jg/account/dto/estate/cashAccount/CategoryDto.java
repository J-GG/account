package fr.jg.account.dto.estate.cashAccount;

import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.LinkedResourceArray;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "categories")
public class CategoryDto extends RepresentationModel<CategoryDto> {

    private Long id;

    private String name;

    private LinkedResource<Long> parent;

    private LinkedResourceArray children;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LinkedResource<Long> getParent() {
        return this.parent;
    }

    public void setParent(final LinkedResource<Long> parent) {
        this.parent = parent;
    }

    public LinkedResourceArray getChildren() {
        return this.children;
    }

    public void setChildren(final LinkedResourceArray children) {
        this.children = children;
    }
}
