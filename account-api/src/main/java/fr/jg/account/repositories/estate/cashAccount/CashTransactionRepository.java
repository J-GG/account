package fr.jg.account.repositories.estate.cashAccount;

import fr.jg.account.models.estate.cashAccount.CashTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CashTransactionRepository extends JpaRepository<CashTransactionModel, UUID> {
    List<CashTransactionModel> findByCashAccountId(UUID cashAccountId);
}
