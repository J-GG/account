package fr.jg.account.mappers;

import fr.jg.account.domain.Account;
import fr.jg.account.dto.AccountDto;
import fr.jg.account.models.AccountModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TransactionMapper.class)
public abstract class AccountMapper {

    public abstract Account modelToDomain(AccountModel accountModel, @Context CycleAvoidingMappingContext context);

    public abstract List<Account> modelToDomain(List<AccountModel> accountModels, @Context CycleAvoidingMappingContext context);

    public abstract AccountDto domainToDto(Account account);

    public abstract List<AccountDto> domainToDto(List<Account> accounts);

    public abstract Account dtoToDomain(AccountDto accountDto);

    public abstract List<Account> dtoToDomain(List<AccountDto> accountDtos);

    public abstract AccountModel domainToModel(Account account, @Context CycleAvoidingMappingContext context);

    public abstract List<AccountModel> domainToModel(List<Account> accounts, @Context CycleAvoidingMappingContext context);
}
