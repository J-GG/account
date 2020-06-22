package fr.jg.account.mappers.estate;

import fr.jg.account.controllers.estate.EstateController;
import fr.jg.account.controllers.user.UserController;
import fr.jg.account.domain.estate.Estate;
import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.estate.EstateDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.mappers.estate.cashAccount.CashAccountMapper;
import fr.jg.account.mappers.estate.tradingAccount.TradingAccountMapper;
import fr.jg.account.mappers.user.UserMapper;
import fr.jg.account.models.estate.EstateModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CashAccountMapper.class, TradingAccountMapper.class})
public abstract class EstateMapper extends AbstractMapper<EstateDto, Estate, EstateModel> {

    @AfterMapping
    void afterMappingDomainToDto(final Estate estate, @MappingTarget final EstateDto estateDto) {
        try {
            estateDto.getCashAccounts().add(linkTo(EstateController.class.getMethod("getCashAccounts", UUID.class), estate.getId()).withSelfRel());

            estateDto.getTradingAccounts().add(linkTo(EstateController.class.getMethod("getTradingAccounts", UUID.class), estate.getId()).withSelfRel());

            final LinkedResource<UUID> linkedUser = new LinkedResource<>(estate.getUser().getId());
            linkedUser.add(linkTo(UserController.class.getMethod("getUser", UUID.class), estate.getUser().getId()).withSelfRel());
            estateDto.setUser(linkedUser);

            estateDto.add(linkTo(EstateController.class.getMethod("getEstate", UUID.class), estate.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
