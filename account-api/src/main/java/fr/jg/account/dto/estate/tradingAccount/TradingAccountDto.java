package fr.jg.account.dto.estate.tradingAccount;

import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.estate.BaseEstateDto;
import fr.jg.account.dto.estate.cashAccount.CashAccountDto;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Relation(collectionRelation = "tradingAccounts")
public class TradingAccountDto extends BaseEstateDto<CashAccountDto> {

    private LinkedResourceArray transactions;

    private Map<String, PortfolioStockDto> portfolio;

    private BigDecimal investedAmount;

    private BigDecimal cashValue;

    private BigDecimal stockValue;

    private BigDecimal balance;

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
        return this.stockValue;
    }

    public void setStockValue(final BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
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
