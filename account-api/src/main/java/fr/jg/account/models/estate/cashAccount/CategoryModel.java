package fr.jg.account.models.estate.cashAccount;

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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryModel parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CategoryModel> children;

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

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public CategoryModel getParent() {
        return this.parent;
    }

    public void setParent(final CategoryModel parent) {
        this.parent = parent;
    }

    public List<CategoryModel> getChildren() {
        return this.children;
    }

    public void setChildren(final List<CategoryModel> children) {
        this.children = children;
    }
}
