package fr.jg.account.domain.estate.cashAccount;

import java.util.List;

public class Category {
    private Long id;

    private String name;

    private Category parent;

    private List<Category> children;

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

    public Category getParent() {
        return this.parent;
    }

    public void setParent(final Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return this.children;
    }

    public void setChildren(final List<Category> children) {
        this.children = children;
    }
}
