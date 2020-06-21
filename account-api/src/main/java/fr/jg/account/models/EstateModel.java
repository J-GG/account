package fr.jg.account.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "estates")
public class EstateModel extends BaseModel {

    @OneToOne
    private UserModel user;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
    private List<TradingAccountModel> tradingAccounts;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
    private List<CashAccountModel> cashAccounts;
    
    public UserModel getUser() {
        return this.user;
    }

    public void setUser(final UserModel user) {
        this.user = user;
    }

    public List<TradingAccountModel> getTradingAccounts() {
        return this.tradingAccounts;
    }

    public void setTradingAccounts(final List<TradingAccountModel> tradingAccounts) {
        this.tradingAccounts = tradingAccounts;
    }

    public List<CashAccountModel> getCashAccounts() {
        return this.cashAccounts;
    }

    public void setCashAccounts(final List<CashAccountModel> cashAccounts) {
        this.cashAccounts = cashAccounts;
    }
}
