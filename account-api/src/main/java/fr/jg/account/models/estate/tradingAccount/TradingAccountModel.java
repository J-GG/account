package fr.jg.account.models.estate.tradingAccount;

import fr.jg.account.models.estate.BaseEstateModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "trading_accounts")
public class TradingAccountModel extends BaseEstateModel {

    @OneToMany(mappedBy = "tradingAccount", cascade = CascadeType.ALL)
    private List<TradingTransactionModel> transactions;

    public List<TradingTransactionModel> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TradingTransactionModel> transactions) {
        this.transactions = transactions;
    }
}
