package fr.jg.account.dto.estate.tradingAccount;

import fr.jg.account.dto.LinkedResource;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Relation(collectionRelation = "wireTransactions")
public class TradingWireTransactionDto extends RepresentationModel<TradingWireTransactionDto> {

    private UUID id;

    private LinkedResource<UUID> tradingAccount;

    private LocalDate date;

    private BigDecimal amount;

    private String comment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LinkedResource<UUID> getTradingAccount() {
        return tradingAccount;
    }

    public void setTradingAccount(LinkedResource<UUID> tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
