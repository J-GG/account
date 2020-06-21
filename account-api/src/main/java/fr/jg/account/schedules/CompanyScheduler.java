package fr.jg.account.schedules;

import fr.jg.account.models.CompanyModel;
import fr.jg.account.repositories.CompanyRepository;
import fr.jg.account.services.FinancialModelingPrepClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CompanyScheduler {

    @Autowired
    FinancialModelingPrepClient financialModelingPrepClient;

    @Autowired
    CompanyRepository companyRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "0 0 1 * * *")
    public void scheduleOutstandingOnceADay() {
        this.financialModelingPrepClient.findAllCompanies().forEach(financialModelingCompanyDto -> {
            if (this.companyRepository.findById(financialModelingCompanyDto.getSymbol()).isEmpty()) {
                final CompanyModel companyModel = new CompanyModel();
                companyModel.setCode(financialModelingCompanyDto.getSymbol());
                companyModel.setName(financialModelingCompanyDto.getName());
                this.companyRepository.save(companyModel);
            }
        });
    }
}
