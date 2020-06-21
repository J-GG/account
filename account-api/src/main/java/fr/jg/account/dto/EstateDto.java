package fr.jg.account.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Relation(collectionRelation = "estates")
public class EstateDto extends RepresentationModel<EstateDto> {

    private UUID id;

    private LinkedResource<UUID> user;

    private LinkedResourceArray cashAccounts;

    private LinkedResourceArray tradingAccounts;

    public UUID getId() {
        return this.id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public LinkedResource<UUID> getUser() {
        return this.user;
    }

    public void setUser(final LinkedResource<UUID> user) {
        this.user = user;
    }

    public LinkedResourceArray getCashAccounts() {
        return this.cashAccounts;
    }

    public void setCashAccounts(final LinkedResourceArray cashAccounts) {
        this.cashAccounts = cashAccounts;
    }

    public LinkedResourceArray getTradingAccounts() {
        return this.tradingAccounts;
    }

    public void setTradingAccounts(final LinkedResourceArray tradingAccounts) {
        this.tradingAccounts = tradingAccounts;
    }
}
