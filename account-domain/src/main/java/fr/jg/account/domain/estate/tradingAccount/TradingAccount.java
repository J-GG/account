package fr.jg.account.domain.estate.tradingAccount;

import fr.jg.account.domain.estate.BaseEstate;

import java.math.BigDecimal;
import java.util.List;

public class TradingAccount extends BaseEstate {

    private List<TradingTransaction> transactions;

//    private Map<String, PortfolioStockDto> portfolio;

    public List<TradingTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(final List<TradingTransaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getCashValue() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getStockValue() {
//        return this.portfolio.values().stream().map(PortfolioStockDto::getCurrentValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getTotalValue() {
        return this.transactions.stream().map(tradingTransaction ->
                tradingTransaction.getUnitPrice().multiply(BigDecimal.valueOf(tradingTransaction.getQuantity()).add(tradingTransaction.getFees()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

