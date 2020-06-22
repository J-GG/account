package fr.jg.account.mappers.estate.loan;

import fr.jg.account.configuration.Configuration;
import fr.jg.account.controllers.estate.loan.AmortizationController;
import fr.jg.account.domain.estate.loan.Amortization;
import fr.jg.account.dto.estate.loan.AmortizationDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public abstract class AmortizationMapper {

    public abstract AmortizationDto domainToDto(Amortization amortization);

    public abstract Amortization dtoToDomain(AmortizationDto amortizationDto);

    @AfterMapping
    void afterMappingDomainToDto(final Amortization amortization, @MappingTarget final AmortizationDto amortizationDto) {
        amortizationDto.setTotalAmount(amortizationDto.getTotalAmount().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
        amortizationDto.setTotalInterest(amortizationDto.getTotalInterest().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
        amortizationDto.getPayments().forEach(paymentDto -> {
            paymentDto.setInterest(paymentDto.getInterest().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
            paymentDto.setPrincipal(paymentDto.getPrincipal().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
            paymentDto.setBalance(paymentDto.getBalance().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
            paymentDto.setAmount(paymentDto.getAmount().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
        });
        amortizationDto.add(linkTo(AmortizationController.class).withSelfRel());
    }

    @AfterMapping
    void afterMappingDtoToDomain(final AmortizationDto amortizationDto, @MappingTarget final Amortization amortization) {
        amortization.setPrincipal(amortization.getPrincipal().setScale(amortization.getCurrency().getDefaultFractionDigits(), Configuration.ROUNDING_MODE));
        amortization.setInterestRate(amortization.getInterestRate().setScale(Configuration.INTERESTED_RATES_PRECISION, Configuration.ROUNDING_MODE));
    }
}
