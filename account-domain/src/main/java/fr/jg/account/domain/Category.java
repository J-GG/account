package fr.jg.account.domain;

import java.util.List;

public class Category {
    private Long id;

    private String name;

    private Category parent;

    private List<Category> children;

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

    public Category getParent() {
        return parent;
    }

    public void setParent(final Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(final List<Category> children) {
        this.children = children;
    }
}
