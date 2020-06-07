package fr.jg.account;

import fr.jg.account.business.AccountBusinessImpl;
import fr.jg.account.business.TransactionBusinessImpl;
import fr.jg.account.ports.primary.AccountBusiness;
import fr.jg.account.ports.primary.TransactionBusiness;
import fr.jg.account.ports.secondary.AccountService;
import fr.jg.account.ports.secondary.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    AccountService accountService;
    
    @Autowired
    TransactionService transactionService;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AccountBusiness accountBusiness() {
        return new AccountBusinessImpl(this.accountService);
    }

    @Bean
    public TransactionBusiness transactionBusiness() {
        return new TransactionBusinessImpl(this.transactionService);
    }
}
