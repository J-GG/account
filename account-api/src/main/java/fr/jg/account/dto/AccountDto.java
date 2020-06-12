package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Relation(collectionRelation = "accounts")
public class AccountDto extends RepresentationModel<AccountDto> {
    private UUID id;

    private String name;

    private LinkedResource<UUID> user;

    private Currency currency;

    private BigDecimal balance;

    private BigDecimal futureBalance;

    private BigDecimal yieldRate;

    private BigDecimal estimatedAnnualYield;

    private LinkedResourceArray transactions;

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LinkedResource<UUID> getUser() {
        return user;
    }

    public void setUser(final LinkedResource<UUID> user) {
        this.user = user;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFutureBalance() {
        return futureBalance;
    }

    public void setFutureBalance(final BigDecimal futureBalance) {
        this.futureBalance = futureBalance;
    }

    public BigDecimal getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(final BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }

    public BigDecimal getEstimatedAnnualYield() {
        return estimatedAnnualYield;
    }

    public void setEstimatedAnnualYield(final BigDecimal estimatedAnnualYield) {
        this.estimatedAnnualYield = estimatedAnnualYield;
    }

    public LinkedResourceArray getTransactions() {
        return transactions;
    }

    public void setTransactions(final LinkedResourceArray transactions) {
        this.transactions = transactions;
    }
}
