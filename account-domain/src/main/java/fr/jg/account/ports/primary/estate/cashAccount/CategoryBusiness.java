package fr.jg.account.ports.primary.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.Category;

import java.util.List;

public interface CategoryBusiness {

    List<Category> getAll();

    Category get(Long categoryId);
}
