package fr.jg.account.repositories;

import fr.jg.account.models.TradingAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TradingAccountRepository extends JpaRepository<TradingAccountModel, UUID> {
}
