package fr.jg.account.controllers;

import fr.jg.account.dto.TransactionDto;
import fr.jg.account.mappers.TransactionMapper;
import fr.jg.account.ports.primary.TransactionBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionBusiness transactionBusiness;

    @GetMapping
    public CollectionModel<TransactionDto> getTransactions() throws NoSuchMethodException {
        final CollectionModel<TransactionDto> transactionDaos = CollectionModel.of(TransactionMapper.INSTANCE.domainToDao(this.transactionBusiness.getAll()));
        transactionDaos.add(linkTo(TransactionController.class.getMethod("getTransactions", UUID.class)).withSelfRel());

        return transactionDaos;
    }
}