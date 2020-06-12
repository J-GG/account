package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "categories")
public class CategoryDto extends RepresentationModel<CategoryDto> {

    private Long id;

    private String name;

    private LinkedResource<Long> parent;

    private LinkedResourceArray children;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LinkedResource<Long> getParent() {
        return parent;
    }

    public void setParent(final LinkedResource<Long> parent) {
        this.parent = parent;
    }

    public LinkedResourceArray getChildren() {
        return children;
    }

    public void setChildren(final LinkedResourceArray children) {
        this.children = children;
    }
}
