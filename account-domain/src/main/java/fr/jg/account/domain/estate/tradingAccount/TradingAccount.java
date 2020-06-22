package fr.jg.account.domain.estate.tradingAccount;

import fr.jg.account.domain.estate.BaseEstate;

import java.util.List;

public class TradingAccount extends BaseEstate {

    private List<TradingTransaction> transactions;

    private List<TradingWireTransaction> wireTransactions;

    public List<TradingTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TradingTransaction> transactions) {
        this.transactions = transactions;
    }

    public List<TradingWireTransaction> getWireTransactions() {
        return this.wireTransactions;
    }

    public void setWireTransactions(final List<TradingWireTransaction> wireTransactions) {
        this.wireTransactions = wireTransactions;
    }
}

