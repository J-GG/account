package fr.jg.account.business;

import fr.jg.account.domain.Category;
import fr.jg.account.ports.primary.CategoryBusiness;
import fr.jg.account.ports.secondary.CategoryService;

import java.util.List;

public class CategoryBusinessImpl implements CategoryBusiness {

    private final CategoryService categoryService;

    public CategoryBusinessImpl(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @Override
    public Category get(final Long categoryId) {
        return this.categoryService.get(categoryId);
    }
}
