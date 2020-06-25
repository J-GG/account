package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.controllers.estate.tradingAccount.StockController;
import fr.jg.account.domain.estate.tradingAccount.Stock;
import fr.jg.account.dto.estate.tradingAccount.StockDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.tradingAccount.StockModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public abstract class StockMapper extends AbstractMapper<StockDto, Stock, StockModel> {

    @AfterMapping
    void afterMappingDomainToModel(final Stock stock, @MappingTarget final StockModel stockModel) {
        if (stock.getDividend() != null) {
            stockModel.setDividend(stock.getDividend().multiply(BigDecimal.valueOf(Math.pow(10, stock.getCurrency().getDefaultFractionDigits()))).longValue());
        }
    }

    @AfterMapping
    void afterMappingModelToDomain(final StockModel stockModel, @MappingTarget final Stock stock) {
        stock.setDividend(BigDecimal.valueOf(stockModel.getDividend()).divide(BigDecimal.valueOf(Math.pow(10, stockModel.getCurrency().getDefaultFractionDigits()))));
    }

    @AfterMapping
    void afterMappingDomainToDto(final Stock stock, @MappingTarget final StockDto stockDto) {
        try {
            stockDto.add(linkTo(StockController.class.getMethod("getStock", String.class), stock.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
