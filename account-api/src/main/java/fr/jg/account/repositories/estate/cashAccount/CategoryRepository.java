package fr.jg.account.repositories.estate.cashAccount;

import fr.jg.account.models.estate.cashAccount.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
