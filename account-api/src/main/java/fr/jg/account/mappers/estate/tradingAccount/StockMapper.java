package fr.jg.account.mappers.estate.tradingAccount;

import fr.jg.account.controllers.estate.tradingAccount.StockController;
import fr.jg.account.domain.estate.tradingAccount.Stock;
import fr.jg.account.dto.estate.tradingAccount.StockDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.tradingAccount.StockModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public abstract class StockMapper extends AbstractMapper<StockDto, Stock, StockModel> {

    @AfterMapping
    void afterMappingDomainToDto(final Stock stock, @MappingTarget final StockDto stockDto) {
        try {
            stockDto.add(linkTo(StockController.class.getMethod("getStock", String.class), stock.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
