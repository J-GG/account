package fr.jg.account.models;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Currency;

@MappedSuperclass
public class BaseEstateModel extends BaseModel {

    @ManyToOne
    private EstateModel estate;

    private String name;

    private Currency currency;

    public EstateModel getEstate() {
        return this.estate;
    }

    public void setEstate(final EstateModel estate) {
        this.estate = estate;
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
}
