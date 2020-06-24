package fr.jg.account.dto.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.TradingOperationEnum;
import fr.jg.account.dto.LinkedResource;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Relation(collectionRelation = "transactions")
public class TradingTransactionDto extends RepresentationModel<TradingTransactionDto> {

    private UUID id;

    private LinkedResource<UUID> tradingAccount;

    private LinkedResource<String> stock;

    private LocalDate date;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal fees;

    private TradingOperationEnum operation;

    private String comment;

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public LinkedResource<UUID> getTradingAccount() {
        return this.tradingAccount;
    }

    public void setTradingAccount(final LinkedResource<UUID> tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public LinkedResource<String> getStock() {
        return this.stock;
    }

    public void setStock(final LinkedResource<String> stock) {
        this.stock = stock;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getFees() {
        return this.fees;
    }

    public void setFees(final BigDecimal fees) {
        this.fees = fees;
    }

    public TradingOperationEnum getOperation() {
        return this.operation;
    }

    public void setOperation(final TradingOperationEnum operation) {
        this.operation = operation;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }
}
