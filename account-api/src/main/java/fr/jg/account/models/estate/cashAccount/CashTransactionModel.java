package fr.jg.account.models.estate.cashAccount;

import fr.jg.account.models.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "cash_transactions")
public class CashTransactionModel extends BaseModel {

    private LocalDate date;

    private Long amount;

    private String comment;

    @ManyToOne
    private CashAccountModel cashAccount;

    @ManyToOne
    private CategoryModel category;

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
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

    public CashAccountModel getCashAccount() {
        return this.cashAccount;
    }

    public void setCashAccount(final CashAccountModel cashAccount) {
        this.cashAccount = cashAccount;
    }

    public CategoryModel getCategory() {
        return this.category;
    }

    public void setCategory(final CategoryModel category) {
        this.category = category;
    }
}
