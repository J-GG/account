package fr.jg.account.models.estate.tradingAccount;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import fr.jg.account.domain.estate.tradingAccount.TradingOperationEnum;
import fr.jg.account.models.BaseModel;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trading_transactions")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class TradingTransactionModel extends BaseModel {

    @ManyToOne
    private TradingAccountModel tradingAccount;

    @ManyToOne
    private StockModel stock;

    private LocalDate date;

    private Long unitPrice;

    private Integer quantity;

    private Long fees;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private TradingOperationEnum operation;

    private String comment;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public TradingAccountModel getTradingAccount() {
        return this.tradingAccount;
    }

    public void setTradingAccount(final TradingAccountModel tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public StockModel getStock() {
        return this.stock;
    }

    public void setStock(final StockModel stock) {
        this.stock = stock;
    }

    public Long getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(final Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Long getFees() {
        return this.fees;
    }

    public void setFees(final Long fees) {
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
