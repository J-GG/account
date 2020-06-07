package fr.jg.account.controllers;

import fr.jg.account.dao.AccountDao;
import fr.jg.account.dao.TransactionDao;
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
    public CollectionModel<AccountDao> getAccounts() throws NoSuchMethodException {
        final CollectionModel<AccountDao> accountDaos = CollectionModel.of(AccountMapper.INSTANCE.domainToDao(this.accountBusiness.getAll()));
        for (final AccountDao accountDao : accountDaos) {
            accountDao.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountDao.getId()).withRel("transactions"));
            accountDao.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountDao.getId()).withSelfRel());
        }
        CollectionModel.of(accountDaos).add(linkTo(AccountController.class).withSelfRel());

        return CollectionModel.of(accountDaos);
    }

    @GetMapping("/{id}")
    public EntityModel<AccountDao> getAccount(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final EntityModel<AccountDao> account = EntityModel.of(AccountMapper.INSTANCE.domainToDao(this.accountBusiness.get(accountId)));
        account.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountId).withRel("transactions"));
        account.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountId).withSelfRel());

        return account;
    }

    @GetMapping("/{id}/transaction")
    public CollectionModel<TransactionDao> getTransactions(@PathVariable("id") final UUID accountId) throws NoSuchMethodException {
        final CollectionModel<TransactionDao> transactionDaos = CollectionModel.of(TransactionMapper.INSTANCE.domainToDao(this.transactionBusiness.getAllByAccountId(accountId)));
        for (final TransactionDao transactionDao : transactionDaos) {
            transactionDao.add(linkTo(AccountController.class.getMethod("getAccount", UUID.class), accountId).withRel("account"));
        }
        transactionDaos.add(linkTo(AccountController.class.getMethod("getTransactions", UUID.class), accountId).withSelfRel());

        return transactionDaos;
    }
}