package fr.jg.account.repositories.estate.tradingAccount;

import fr.jg.account.models.estate.tradingAccount.TradingTransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TradingTransactionRepository extends JpaRepository<TradingTransactionModel, UUID> {
    List<TradingTransactionModel> findByTradingAccountId(UUID tradingAccountId);
}
