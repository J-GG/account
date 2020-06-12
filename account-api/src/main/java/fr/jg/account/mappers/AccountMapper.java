package fr.jg.account.mappers;

import fr.jg.account.controllers.AccountController;
import fr.jg.account.controllers.UserController;
import fr.jg.account.domain.Account;
import fr.jg.account.domain.Transaction;
import fr.jg.account.dto.AccountDto;
import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.models.AccountModel;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class, UserMapper.class})
public abstract class AccountMapper {

    public abstract Account modelToDomain(AccountModel accountModel, @Context CycleAvoidingMappingContext context);

    public abstract List<Account> modelToDomain(List<AccountModel> accountModels, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "transactions", ignore = true)
    public abstract AccountDto domainToDto(Account account);

    public abstract List<AccountDto> domainToDto(List<Account> accounts);

    @Mapping(target = "transactions", ignore = true)
    public abstract Account dtoToDomain(AccountDto accountDto);

    public abstract List<Account> dtoToDomain(List<AccountDto> accountDtos);

    public abstract AccountModel domainToModel(Account account, @Context CycleAvoidingMappingContext context);

    public abstract List<AccountModel> domainToModel(List<Account> accounts, @Context CycleAvoidingMappingContext context);

    @AfterMapping
    void afterMappingDomainToModel(final Account account, @MappingTarget final AccountModel accountModel) {
        accountModel.setYieldRate(account.getYieldRate().multiply(BigDecimal.valueOf(1000)).longValue());
    }

    @AfterMapping
    void afterMappingModelToDomain(final AccountModel accountModel, @MappingTarget final Account account) {
        account.setYieldRate(new BigDecimal(accountModel.getYieldRate()).divide(BigDecimal.valueOf(1000)));
    }

    @AfterMapping
    void afterMappingDomainToDto(final Account account, @MappingTarget final AccountDto accountDto) {
        accountDto.setBalance(account.getTransactions().stream()
                .filter(transaction -> transaction.getDate().isBefore(LocalDate.now()))
                .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));

        accountDto.setFutureBalance(account.getTransactions().stream()
                .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));

        accountDto.setEstimatedAnnualYield(accountDto.getBalance()
                .multiply(account.getYieldRate())
                .divide(new BigDecimal(100), account.getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_EVEN)
        );

        try {
            final LinkedResourceArray linkedTransactions = new LinkedResourceArray(account.getTransactions().size());
            linkedTransactions.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), account.getId()).withSelfRel());
            accountDto.setTransactions(linkedTransactions);

            final LinkedResource<UUID> linkedUser = new LinkedResource<>(account.getUser().getId());
            linkedUser.add(linkTo(UserController.class.getMethod("getUser", UUID.class), account.getUser().getId()).withSelfRel());
            accountDto.setUser(linkedUser);

            accountDto.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), account.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
