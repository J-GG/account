package fr.jg.account.models.estate.tradingAccount;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "stocks")
public class StockModel {

    @Id
    private String id;

    private String name;

    private Currency currency;

    private Long dividend;

    @OneToMany(mappedBy = "stock")
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

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public Long getDividend() {
        return this.dividend;
    }

    public void setDividend(final Long dividend) {
        this.dividend = dividend;
    }
}
