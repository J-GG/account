package fr.jg.account.controllers;

import fr.jg.account.dto.CashAccountDto;
import fr.jg.account.dto.EstateDto;
import fr.jg.account.dto.TradingAccountDto;
import fr.jg.account.mappers.CashAccountMapper;
import fr.jg.account.mappers.EstateMapper;
import fr.jg.account.mappers.TradingAccountMapper;
import fr.jg.account.ports.primary.EstateBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/estates")
public class EstateController {

    @Autowired
    private EstateBusiness estateBusiness;

    @Autowired
    private EstateMapper estateMapper;

    @Autowired
    private CashAccountMapper cashAccountMapper;

    @Autowired
    private TradingAccountMapper tradingAccountMapper;

    @GetMapping
    public CollectionModel<EstateDto> getEstates() {
        return this.estateMapper.domainToCollectionModel(this.estateBusiness.getAll(), linkTo(EstateController.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public EstateDto getEstate(@PathVariable("id") final UUID estateId) {
        return this.estateMapper.domainToDto(this.estateBusiness.get(estateId));
    }

    @GetMapping("/{id}/cashAccounts")
    public CollectionModel<CashAccountDto> getCashAccounts(@PathVariable("id") final UUID estateId) throws NoSuchMethodException {
        return this.cashAccountMapper.domainToCollectionModel(this.estateBusiness.get(estateId).getCashAccounts(),
                linkTo(EstateController.class.getMethod("getCashAccounts", UUID.class), estateId).withSelfRel()
        );
    }

    @PostMapping("/{id}/cashAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CashAccountDto postCashAccount(@PathVariable("id") final UUID estateId, @RequestBody final CashAccountDto cashAccountDto) {
        return this.cashAccountMapper.domainToDto(this.estateBusiness.addCashAccountToEstate(this.cashAccountMapper.dtoToDomain(cashAccountDto), estateId));
    }

    @GetMapping("/{id}/tradingAccounts")
    public CollectionModel<TradingAccountDto> getTradingAccounts(@PathVariable("id") final UUID estateId) throws NoSuchMethodException {
        return this.tradingAccountMapper.domainToCollectionModel(this.estateBusiness.get(estateId).getTradingAccounts(),
                linkTo(EstateController.class.getMethod("getTradingAccounts", UUID.class), estateId).withSelfRel()
        );
    }

    @PostMapping("/{id}/tradingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public TradingAccountDto postTradingAccount(@PathVariable("id") final UUID estateId, @RequestBody final TradingAccountDto tradingAccountDto) {
        return this.tradingAccountMapper.domainToDto(this.estateBusiness.addTradingAccountToEstate(this.tradingAccountMapper.dtoToDomain(tradingAccountDto), estateId));
    }
}