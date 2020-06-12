package fr.jg.account.ports.secondary;

import fr.jg.account.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category get(Long categoryId);
}
