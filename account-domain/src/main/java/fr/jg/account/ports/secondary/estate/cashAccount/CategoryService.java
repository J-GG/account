package fr.jg.account.ports.secondary.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category get(Long categoryId);
}
