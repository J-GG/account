package fr.jg.account.schedules;

import fr.jg.account.models.estate.tradingAccount.StockModel;
import fr.jg.account.repositories.estate.tradingAccount.StockRepository;
import fr.jg.account.services.estate.tradingAccount.FinancialModelingPrepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockScheduler {

    @Autowired
    FinancialModelingPrepClient financialModelingPrepClient;

    @Autowired
    StockRepository stockRepository;

    //    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 1 * * *")
    public void scheduleOutstandingOnceADay() {
        this.financialModelingPrepClient.findAllCompanies().forEach(financialModelingCompanyDto -> {
            if (this.stockRepository.findById(financialModelingCompanyDto.getSymbol()).isEmpty()) {
                if (financialModelingCompanyDto.getSymbol() != null && financialModelingCompanyDto.getName() != null) {
                    final StockModel stockModel = new StockModel();
                    stockModel.setId(financialModelingCompanyDto.getSymbol());
                    stockModel.setName(financialModelingCompanyDto.getName());
                    this.stockRepository.save(stockModel);
                }
            }
        });
    }
}
