package fr.jg.account.repositories;

import fr.jg.account.models.CashTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<CashTransactionModel, UUID> {
    List<CashTransactionModel> findByCashAccountId(UUID cashAccountId);
}
