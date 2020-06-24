package fr.jg.account.schedules;

import fr.jg.account.domain.estate.tradingAccount.Stock;
import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingDividend;
import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingHistoricalDividend;
import fr.jg.account.dto.estate.tradingAccount.financialmodeling.FinancialModelingHistoricalDividendList;
import fr.jg.account.ports.primary.estate.tradingAccount.StockBusiness;
import fr.jg.account.services.estate.tradingAccount.FinancialModelingPrepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StockScheduler {

    @Autowired
    StockBusiness stockBusiness;

    @Autowired
    FinancialModelingPrepClient financialModelingPrepClient;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 1 * * FRI")
    public void scheduleOutstandingOnceADay() {
        final List<Stock> stocks = new ArrayList<>();
        this.financialModelingPrepClient.findAllStocks().forEach(financialModelingCompanyDto -> {
            if (financialModelingCompanyDto.getSymbol() != null && financialModelingCompanyDto.getName() != null) {
                final Stock stock = new Stock();
                stock.setId(financialModelingCompanyDto.getSymbol());
                stock.setName(financialModelingCompanyDto.getName());
                stocks.add(stock);
            }
        });

        final List<Stock> existingStocks = this.stockBusiness.getAll();

        final List<Stock> stocksToAdd = stocks.stream().filter(newStock -> existingStocks.stream().noneMatch(oldStock -> oldStock.getId().equals(newStock.getId()))).collect(Collectors.toList());

        final int batchSize = 5;
        IntStream.range(0, (stocksToAdd.size() + batchSize - 1) / batchSize)
                .mapToObj(i -> stocksToAdd.subList(i * batchSize, Math.min(stocksToAdd.size(), (i + 1) * batchSize)))
                .forEach(batch -> {
                    final String stockIds = batch.stream().map(Stock::getId).collect(Collectors.joining(","));
                    final FinancialModelingHistoricalDividendList financialModelingHistoricalDividendList = this.financialModelingPrepClient.findStockDividends(stockIds);
                    if (financialModelingHistoricalDividendList.getHistoricalStockList() != null) {
                        batch.forEach(stock -> {
                            final FinancialModelingHistoricalDividend financialModelingHistoricalDividends = financialModelingHistoricalDividendList.getHistoricalStockList().stream()
                                    .filter(financialModelingHistoricalDividend -> financialModelingHistoricalDividend.getSymbol().equals(stock.getId()))
                                    .findFirst().orElse(null);

                            if (financialModelingHistoricalDividends != null) {
                                final BigDecimal dividend = financialModelingHistoricalDividends.getHistorical().stream()
                                        .filter(otherFinancialModelingDividend -> otherFinancialModelingDividend.getDate().isAfter(LocalDate.now().minusYears(1)))
                                        .map(FinancialModelingDividend::getAdjDividend)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                stock.setDividend(dividend);
                            } else {
                                stock.setDividend(BigDecimal.ZERO);
                            }

                            stock.setCurrency(Currency.getInstance("EUR"));
                            this.stockBusiness.create(stock);
                        });
                    }
                });
    }
}
