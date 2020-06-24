package fr.jg.account.dto.estate.tradingAccount.financialmodeling;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FinancialModelingHistoricalStockList {

    private List<FinancialModelingHistoricalStock> historicalStockList;

    public List<FinancialModelingHistoricalStock> getHistoricalStockList() {
        return this.historicalStockList;
    }

    public void setHistoricalStockList(final List<FinancialModelingHistoricalStock> historicalStockList) {
        this.historicalStockList = historicalStockList;
    }

    public static class FinancialModelingHistoricalStock {
        private String symbol;

        private List<HistoricalStockDay> historical;

        public String getSymbol() {
            return this.symbol;
        }

        public void setSymbol(final String symbol) {
            this.symbol = symbol;
        }

        public List<HistoricalStockDay> getHistorical() {
            return this.historical;
        }

        public void setHistorical(final List<HistoricalStockDay> historical) {
            this.historical = historical;
        }

        public static class HistoricalStockDay {
            private LocalDate date;

            private BigDecimal close;

            public LocalDate getDate() {
                return this.date;
            }

            public void setDate(final LocalDate date) {
                this.date = date;
            }

            public BigDecimal getClose() {
                return this.close;
            }

            public void setClose(final BigDecimal close) {
                this.close = close;
            }
        }
    }
}
