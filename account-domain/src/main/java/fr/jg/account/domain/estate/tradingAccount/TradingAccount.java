package fr.jg.account.domain.estate.tradingAccount;

import fr.jg.account.domain.estate.BaseEstate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TradingAccount extends BaseEstate {

    private List<TradingTransaction> transactions;

    private Map<String, PortfolioStock> portfolio;

    public List<TradingTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TradingTransaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getStockValue() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getCashValue() {
        final BigDecimal positiveAmount = this.transactions.stream()
                .filter(transaction -> Arrays.asList(TradingOperationEnum.WIRE_TRANSFER, TradingOperationEnum.SALE).contains(transaction.getOperation()))
                .map(transaction -> transaction.getUnitPrice().multiply(BigDecimal.valueOf(transaction.getQuantity())).add(transaction.getFees()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal negativeAmount = this.transactions.stream()
                .filter(transaction -> Arrays.asList(TradingOperationEnum.TAX, TradingOperationEnum.BUYING).contains(transaction.getOperation()))
                .map(transaction -> transaction.getUnitPrice().multiply(BigDecimal.valueOf(transaction.getQuantity())).add(transaction.getFees()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return positiveAmount.subtract(negativeAmount);
    }

    public BigDecimal getInvestedAmount() {
        return this.transactions.stream()
                .filter(transaction -> TradingOperationEnum.WIRE_TRANSFER.equals(transaction.getOperation()))
                .map(transaction -> transaction.getUnitPrice().multiply(BigDecimal.valueOf(transaction.getQuantity()).add(transaction.getFees())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getBalance() {
        return this.getCashValue().add(getStockValue());
    }

    public BigDecimal getEstimatedAnnualYield() {
        return BigDecimal.ZERO;
    }

    public Map<String, PortfolioStock> getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(final Map<String, PortfolioStock> portfolio) {
        this.portfolio = portfolio;
    }
}

