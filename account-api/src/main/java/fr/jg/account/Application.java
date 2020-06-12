package fr.jg.account;

import fr.jg.account.business.AccountBusinessImpl;
import fr.jg.account.business.CategoryBusinessImpl;
import fr.jg.account.business.TransactionBusinessImpl;
import fr.jg.account.business.UserBusinessImpl;
import fr.jg.account.ports.primary.AccountBusiness;
import fr.jg.account.ports.primary.CategoryBusiness;
import fr.jg.account.ports.primary.TransactionBusiness;
import fr.jg.account.ports.primary.UserBusiness;
import fr.jg.account.ports.secondary.AccountService;
import fr.jg.account.ports.secondary.CategoryService;
import fr.jg.account.ports.secondary.TransactionService;
import fr.jg.account.ports.secondary.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

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

    @Bean
    public CategoryBusiness categoryBusiness() {
        return new CategoryBusinessImpl(this.categoryService);
    }

    @Bean
    public UserBusiness userBusiness() {
        return new UserBusinessImpl(this.userService);
    }
}
