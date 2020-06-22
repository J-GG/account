package fr.jg.account.models.estate.tradingAccount;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "stocks")
public class StockModel {

    @Id
    private String id;

    private String name;

    @OneToMany
    private List<TradingTransactionModel> tradingTransactions;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<TradingTransactionModel> getTradingTransactions() {
        return this.tradingTransactions;
    }

    public void setTradingTransactions(final List<TradingTransactionModel> tradingTransactions) {
        this.tradingTransactions = tradingTransactions;
    }
}
