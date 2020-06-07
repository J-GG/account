package fr.jg.account.mappers;

import fr.jg.account.dao.AccountDao;
import fr.jg.account.domain.Account;
import fr.jg.account.models.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account modelToDomain(AccountModel accountModel);

    List<Account> modelToDomain(List<AccountModel> accountModels);

    AccountDao domainToDao(Account account);

    List<AccountDao> domainToDao(List<Account> accounts);

    Account daoToDomain(AccountDao accountDao);

    List<Account> daoToDomain(List<AccountDao> accountDaos);

    AccountModel domainToModel(Account account);

    List<AccountModel> domainToModel(List<Account> accounts);
}
