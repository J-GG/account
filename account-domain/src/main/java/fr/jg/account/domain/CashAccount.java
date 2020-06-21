package fr.jg.account.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CashAccount extends BaseEstate {

    private BigDecimal yieldRate;

    private List<CashTransaction> transactions;

    public CashAccount() {
        this.transactions = new ArrayList<>();
    }

    public BigDecimal getYieldRate() {
        return this.yieldRate;
    }

    public void setYieldRate(final BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }

    public List<CashTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<CashTransaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(final CashTransaction transactions) {
        this.transactions.add(transactions);
    }
}

