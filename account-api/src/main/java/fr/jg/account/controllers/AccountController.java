package fr.jg.account.controllers;

import fr.jg.account.dto.AccountDto;
import fr.jg.account.dto.TransactionDto;
import fr.jg.account.mappers.AccountMapper;
import fr.jg.account.mappers.TransactionMapper;
import fr.jg.account.ports.primary.AccountBusiness;
import fr.jg.account.ports.primary.TransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountBusiness accountBusiness;

    @Autowired
    TransactionBusiness transactionBusiness;

    @GetMapping
    public CollectionModel<AccountDto> getAccounts() throws NoSuchMethodException {
        final CollectionModel<AccountDto> accountDaos = CollectionModel.of(AccountMapper.INSTANCE.domainToDao(this.accountBusiness.getAll()));
        for (final AccountDto accountDto : accountDaos) {
            accountDto.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountDto.getId()).withRel("transactions"));
            accountDto.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountDto.getId()).withSelfRel());
        }
        CollectionModel.of(accountDaos).add(linkTo(AccountController.class).withSelfRel());

        return CollectionModel.of(accountDaos);
    }

    @GetMapping("/{id}")
    public EntityModel<AccountDto> getAccount(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final EntityModel<AccountDto> account = EntityModel.of(AccountMapper.INSTANCE.domainToDao(this.accountBusiness.get(accountId)));
        account.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountId).withRel("transactions"));
        account.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountId).withSelfRel());

        return account;
    }

    @GetMapping("/{id}/transaction")
    public CollectionModel<TransactionDto> getTransactions(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final CollectionModel<TransactionDto> transactionDaos = CollectionModel.of(TransactionMapper.INSTANCE.domainToDao(this.transactionBusiness.getAllByAccountId(accountId)));
        for (final TransactionDto transactionDto : transactionDaos) {
            transactionDto.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountId).withRel("account"));
        }
        transactionDaos.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountId).withSelfRel());

        return transactionDaos;
    }
}