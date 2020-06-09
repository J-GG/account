package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class AccountDto extends RepresentationModel<AccountDto> {
    private UUID id;

    private String name;

    private Currency currency;

    private BigDecimal balance;

    private BigDecimal futureBalance;

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

    public void setFutureBalance(BigDecimal futureBalance) {
        this.futureBalance = futureBalance;
    }
}
