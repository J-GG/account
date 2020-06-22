package fr.jg.account.repositories.estate.tradingAccount;

import fr.jg.account.models.estate.tradingAccount.StockModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<StockModel, String> {
}
