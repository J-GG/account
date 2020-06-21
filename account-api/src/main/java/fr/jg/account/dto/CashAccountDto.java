package fr.jg.account.dto;

import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "cashAccounts")
public class CashAccountDto extends BaseEstateDto<CashAccountDto> {

    private BigDecimal balance;

    private BigDecimal futureBalance;

    private BigDecimal yieldRate;

    private BigDecimal estimatedAnnualYield;

    private LinkedResourceArray transactions;

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFutureBalance() {
        return this.futureBalance;
    }

    public void setFutureBalance(final BigDecimal futureBalance) {
        this.futureBalance = futureBalance;
    }

    public BigDecimal getYieldRate() {
        return this.yieldRate;
    }

    public void setYieldRate(final BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }

    public BigDecimal getEstimatedAnnualYield() {
        return this.estimatedAnnualYield;
    }

    public void setEstimatedAnnualYield(final BigDecimal estimatedAnnualYield) {
        this.estimatedAnnualYield = estimatedAnnualYield;
    }

    public LinkedResourceArray getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final LinkedResourceArray transactions) {
        this.transactions = transactions;
    }
}
