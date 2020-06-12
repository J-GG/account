package fr.jg.account.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryModel {

    @Id
    private Long id;

    private String name;

    @Transient
    private Long parentId;

    @ManyToOne(targetEntity = CategoryModel.class)
    @JoinColumn(name = "parent_id")
    private CategoryModel parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CategoryModel> children;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public CategoryModel getParent() {
        return parent;
    }

    public void setParent(final CategoryModel parent) {
        this.parent = parent;
    }

    public List<CategoryModel> getChildren() {
        return children;
    }

    public void setChildren(final List<CategoryModel> children) {
        this.children = children;
    }
}
