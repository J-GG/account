package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.controllers.estate.EstateController;
import fr.jg.account.controllers.estate.tradingAccount.TradingAccountController;
import fr.jg.account.domain.estate.tradingAccount.PortfolioStock;
import fr.jg.account.domain.estate.tradingAccount.TradingAccount;
import fr.jg.account.domain.estate.tradingAccount.TradingOperationEnum;
import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.estate.tradingAccount.TradingAccountDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.mappers.estate.EstateMapper;
import fr.jg.account.models.estate.tradingAccount.TradingAccountModel;
import fr.jg.account.ports.primary.estate.tradingAccount.StockBusiness;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {EstateMapper.class, TradingTransactionMapper.class})
public abstract class TradingAccountMapper extends AbstractMapper<TradingAccountDto, TradingAccount, TradingAccountModel> {

    @Autowired
    private StockBusiness stockBusiness;

    @AfterMapping
    void afterMappingModelToDomain(final TradingAccountModel tradingAccountModel, @MappingTarget final TradingAccount tradingAccount) {
        final Map<String, PortfolioStock> portfolio = new HashMap<>();
        tradingAccount.getTransactions().forEach(transaction -> {
            if (TradingOperationEnum.BUYING.equals(transaction.getOperation())) {
                PortfolioStock stock = portfolio.get(transaction.getStock().getId());
                if (stock == null) {
                    stock = new PortfolioStock();
                    stock.setCode(transaction.getStock().getId());
                    stock.setName(this.stockBusiness.get(transaction.getStock().getId()).getName());
                }
                stock.setQuantity(stock.getQuantity() + transaction.getQuantity());
                stock.setInvestedAmount(stock.getInvestedAmount().add(transaction.getUnitPrice().multiply(BigDecimal.valueOf(transaction.getQuantity())).add(transaction.getFees())));
                portfolio.put(transaction.getStock().getId(), stock);
            }
        });
        tradingAccount.setPortfolio(portfolio);
    }

    @AfterMapping
    void afterMappingDomainToDto(final TradingAccount tradingAccount, @MappingTarget final TradingAccountDto tradingAccountDto) {
        try {
            final LinkedResourceArray linkedTransactions = new LinkedResourceArray(tradingAccount.getTransactions().size());
            linkedTransactions.add(linkTo(TradingAccountController.class.getMethod("getTransactions", UUID.class), tradingAccount.getId()).withSelfRel());
            tradingAccountDto.setTransactions(linkedTransactions);

            final LinkedResource<UUID> linkedEstate = new LinkedResource<>(tradingAccount.getEstate().getId());
            linkedEstate.add(linkTo(EstateController.class.getMethod("getEstate", UUID.class), tradingAccount.getEstate().getId()).withSelfRel());
            tradingAccountDto.setEstate(linkedEstate);

            tradingAccountDto.add(linkTo(TradingAccountController.class.getMethod("getTradingAccount", UUID.class), tradingAccount.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
