package fr.jg.account;

import fr.jg.account.business.estate.EstateBusinessImpl;
import fr.jg.account.business.estate.cashAccount.CashAccountBusinessImpl;
import fr.jg.account.business.estate.cashAccount.CashTransactionBusinessImpl;
import fr.jg.account.business.estate.cashAccount.CategoryBusinessImpl;
import fr.jg.account.business.estate.loan.AmortizationBusinessImpl;
import fr.jg.account.business.estate.tradingAccount.StockBusinessImpl;
import fr.jg.account.business.estate.tradingAccount.TradingAccountBusinessImpl;
import fr.jg.account.business.estate.tradingAccount.TradingTransactionBusinessImpl;
import fr.jg.account.business.estate.tradingAccount.TradingWireTransactionBusinessImpl;
import fr.jg.account.business.user.UserBusinessImpl;
import fr.jg.account.ports.primary.estate.EstateBusiness;
import fr.jg.account.ports.primary.estate.cashAccount.CashAccountBusiness;
import fr.jg.account.ports.primary.estate.cashAccount.CashTransactionBusiness;
import fr.jg.account.ports.primary.estate.cashAccount.CategoryBusiness;
import fr.jg.account.ports.primary.estate.loan.AmortizationBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.StockBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingAccountBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingTransactionBusiness;
import fr.jg.account.ports.primary.estate.tradingAccount.TradingWireTransactionBusiness;
import fr.jg.account.ports.primary.user.UserBusiness;
import fr.jg.account.ports.secondary.estate.EstateService;
import fr.jg.account.ports.secondary.estate.cashAccount.CashAccountService;
import fr.jg.account.ports.secondary.estate.cashAccount.CashTransactionService;
import fr.jg.account.ports.secondary.estate.cashAccount.CategoryService;
import fr.jg.account.ports.secondary.estate.tradingAccount.StockService;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingAccountService;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingTransactionService;
import fr.jg.account.ports.secondary.estate.tradingAccount.TradingWireTransactionService;
import fr.jg.account.ports.secondary.user.UserService;
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
    private StockService stockService;

    @Autowired
    private EstateService estateService;

    @Autowired
    private TradingAccountService tradingAccountService;

    @Autowired
    private TradingTransactionService tradingTransactionService;

    @Autowired
    private TradingWireTransactionService tradingWireTransactionService;

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
    public TradingTransactionBusiness tradingTransactionBusiness() {
        return new TradingTransactionBusinessImpl(this.tradingTransactionService);
    }

    @Bean
    public TradingWireTransactionBusiness tradingWireTransactionBusiness() {
        return new TradingWireTransactionBusinessImpl(this.tradingWireTransactionService);
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
    public StockBusiness companyBusiness() {
        return new StockBusinessImpl(this.stockService);
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
