package fr.jg.account.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cash_accounts")
public class CashAccountModel extends BaseEstateModel {

    private Long yieldRate;

    @OneToMany(mappedBy = "cashAccount", cascade = CascadeType.ALL)
    private List<CashTransactionModel> transactions;

    public Long getYieldRate() {
        return this.yieldRate;
    }

    public void setYieldRate(final Long yieldRate) {
        this.yieldRate = yieldRate;
    }

    public List<CashTransactionModel> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<CashTransactionModel> transactions) {
        this.transactions = transactions;
    }
}
