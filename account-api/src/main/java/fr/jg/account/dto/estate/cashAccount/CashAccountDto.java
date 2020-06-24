package fr.jg.account.dto.estate.cashAccount;

import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.estate.BaseEstateDto;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "cashAccounts")
public class CashAccountDto extends BaseEstateDto<CashAccountDto> {

    private LinkedResourceArray transactions;

    private BigDecimal yieldRate;

    private BigDecimal balance;

    private BigDecimal futureBalance;

    private BigDecimal estimatedAnnualYield;

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
