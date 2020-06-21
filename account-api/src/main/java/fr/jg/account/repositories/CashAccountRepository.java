package fr.jg.account.repositories;

import fr.jg.account.models.CashAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccountModel, UUID> {
}
