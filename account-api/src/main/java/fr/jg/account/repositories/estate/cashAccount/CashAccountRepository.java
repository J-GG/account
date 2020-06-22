package fr.jg.account.repositories.estate.cashAccount;

import fr.jg.account.models.estate.cashAccount.CashAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccountModel, UUID> {
}
