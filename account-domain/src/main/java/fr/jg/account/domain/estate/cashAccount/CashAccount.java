package fr.jg.account.domain.estate.cashAccount;

import fr.jg.account.configuration.Configuration;
import fr.jg.account.domain.estate.BaseEstate;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Override
    public BigDecimal getBalance() {
        return this.transactions.stream()
                .filter(transaction -> transaction.getDate().isBefore(LocalDate.now()))
                .map(CashTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getFutureBalance() {
        return this.transactions.stream()
                .map(CashTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getEstimatedAnnualYield() {
        return getBalance()
                .multiply(this.yieldRate)
                .divide(BigDecimal.valueOf(100), this.currency.getDefaultFractionDigits(), Configuration.ROUNDING_MODE);
    }
}

