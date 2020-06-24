package fr.jg.account.dto.estate.tradingAccount;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.Currency;

@Relation(collectionRelation = "stocks")
public class StockDto extends RepresentationModel<StockDto> {

    private String id;

    private String name;

    private Currency currency;

    private BigDecimal dividend;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
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

    public BigDecimal getDividend() {
        return this.dividend;
    }

    public void setDividend(final BigDecimal dividend) {
        this.dividend = dividend;
    }
}
