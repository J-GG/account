package fr.jg.account.ports.primary;

import fr.jg.account.domain.Category;

import java.util.List;

public interface CategoryBusiness {

    List<Category> getAll();

    Category get(Long categoryId);
}
