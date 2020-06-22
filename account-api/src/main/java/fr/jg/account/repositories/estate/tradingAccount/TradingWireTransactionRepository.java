package fr.jg.account.repositories.estate.tradingAccount;

import fr.jg.account.models.estate.tradingAccount.TradingWireTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TradingWireTransactionRepository extends JpaRepository<TradingWireTransactionModel, UUID> {
}
