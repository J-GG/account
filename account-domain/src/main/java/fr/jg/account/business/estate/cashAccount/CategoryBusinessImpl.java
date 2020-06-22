package fr.jg.account.business.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.Category;
import fr.jg.account.ports.primary.estate.cashAccount.CategoryBusiness;
import fr.jg.account.ports.secondary.estate.cashAccount.CategoryService;

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
