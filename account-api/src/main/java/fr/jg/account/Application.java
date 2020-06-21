package fr.jg.account;

import fr.jg.account.business.*;
import fr.jg.account.ports.primary.*;
import fr.jg.account.ports.secondary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class Application {

    @Autowired
    private CashAccountService cashAccountService;

    @Autowired
    private CashTransactionService cashTransactionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EstateService estateService;

    @Autowired
    private TradingAccountService tradingAccountService;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CashAccountBusiness cashAccountBusiness() {
        return new CashAccountBusinessImpl(this.cashAccountService, cashTransactionBusiness());
    }

    @Bean
    public TradingAccountBusiness tradingAccountBusiness() {
        return new TradingAccountBusinessImpl(this.tradingAccountService);
    }

    @Bean
    public CashTransactionBusiness cashTransactionBusiness() {
        return new CashTransactionBusinessImpl(this.cashTransactionService);
    }

    @Bean
    public CategoryBusiness categoryBusiness() {
        return new CategoryBusinessImpl(this.categoryService);
    }

    @Bean
    public UserBusiness userBusiness() {
        return new UserBusinessImpl(this.userService, estateBusiness());
    }

    @Bean
    public AmortizationBusiness amortizationBusiness() {
        return new AmortizationBusinessImpl();
    }

    @Bean
    public CompanyBusiness companyBusiness() {
        return new CompanyBusinessImpl(this.companyService);
    }

    @Bean
    public EstateBusiness estateBusiness() {
        return new EstateBusinessImpl(this.estateService, cashAccountBusiness(), tradingAccountBusiness());
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
