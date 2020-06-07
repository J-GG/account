package fr.jg.account.mappers;

import fr.jg.account.domain.Account;
import fr.jg.account.dto.AccountDto;
import fr.jg.account.models.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account modelToDomain(AccountModel accountModel);

    List<Account> modelToDomain(List<AccountModel> accountModels);

    AccountDto domainToDao(Account account);

    List<AccountDto> domainToDao(List<Account> accounts);

    Account daoToDomain(AccountDto accountDto);

    List<Account> daoToDomain(List<AccountDto> accountDtos);

    AccountModel domainToModel(Account account);

    List<AccountModel> domainToModel(List<Account> accounts);
}
