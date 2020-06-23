package fr.jg.account.dto.estate.tradingAccount;

import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.estate.BaseEstateDto;
import fr.jg.account.dto.estate.cashAccount.CashAccountDto;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Relation(collectionRelation = "tradingAccounts")
public class TradingAccountDto extends BaseEstateDto<CashAccountDto> {

    private LinkedResourceArray wireTransactions;

    private LinkedResourceArray transactions;

    private Map<String, PortfolioStockDto> portfolio;

    private BigDecimal investedAmount;

    private BigDecimal cashValue;

    public TradingAccountDto() {
        this.investedAmount = BigDecimal.ZERO;
        this.cashValue = BigDecimal.ZERO;
        this.portfolio = new HashMap<>();
    }

    public LinkedResourceArray getWireTransactions() {
        return wireTransactions;
    }

    public void setWireTransactions(LinkedResourceArray wireTransactions) {
        this.wireTransactions = wireTransactions;
    }

    public LinkedResourceArray getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final LinkedResourceArray transactions) {
        this.transactions = transactions;
    }

    public Map<String, PortfolioStockDto> getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(final Map<String, PortfolioStockDto> portfolio) {
        this.portfolio = portfolio;
    }

    public BigDecimal getInvestedAmount() {
        return this.investedAmount;
    }

    public void setInvestedAmount(final BigDecimal investedAmount) {
        this.investedAmount = investedAmount;
    }

    public BigDecimal getCashValue() {
        return this.cashValue;
    }

    public void setCashValue(final BigDecimal cashValue) {
        this.cashValue = cashValue;
    }

    public BigDecimal getStockValue() {
        return this.portfolio.values().stream().map(PortfolioStockDto::getCurrentValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalValue() {
        return this.cashValue.add(this.getStockValue());
    }

    public static class TransactionDto {

        private UUID id;

        private LocalDate date;

        private TradingOperationDto operation;

        public UUID getId() {
            return this.id;
        }

        public void setId(final UUID id) {
            this.id = id;
        }

        public LocalDate getDate() {
            return this.date;
        }

        public void setDate(final LocalDate date) {
            this.date = date;
        }

        public TradingOperationDto getOperation() {
            return this.operation;
        }

        public void setOperation(final TradingOperationDto operation) {
            this.operation = operation;
        }
    }
}
