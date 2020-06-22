package fr.jg.account.models.estate.tradingAccount;

import fr.jg.account.models.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "trading_wire_transactions")
public class TradingWireTransactionModel extends BaseModel {

    @ManyToOne
    private TradingAccountModel tradingAccount;

    private LocalDate date;

    private Long amount;

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

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }
}
