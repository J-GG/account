package fr.jg.account.domain.estate;

import fr.jg.account.domain.estate.cashAccount.CashAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Estate {

    private UUID id;

    private User user;

    private List<CashAccount> cashAccounts;

    private List<TradingAccount> tradingAccounts;

    public Estate() {
        this.cashAccounts = new ArrayList<>();
        this.tradingAccounts = new ArrayList<>();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<CashAccount> getCashAccounts() {
        return this.cashAccounts;
    }

    public void setCashAccounts(final List<CashAccount> cashAccounts) {
        this.cashAccounts = cashAccounts;
    }

    public List<TradingAccount> getTradingAccounts() {
        return this.tradingAccounts;
    }

    public void setTradingAccounts(final List<TradingAccount> tradingAccounts) {
        this.tradingAccounts = tradingAccounts;
    }
}

