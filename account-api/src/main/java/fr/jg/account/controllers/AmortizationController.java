package fr.jg.account.controllers;

import fr.jg.account.dto.AmortizationDto;
import fr.jg.account.mappers.AmortizationMapper;
import fr.jg.account.ports.primary.AmortizationBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amortization")
public class AmortizationController {

    @Autowired
    AmortizationBusiness amortizationBusiness;

    @Autowired
    AmortizationMapper amortizationMapper;

    @PostMapping
    public AmortizationDto postAmortization(@RequestBody final AmortizationDto amortizationDto) {
        return this.amortizationMapper.domainToDto(this.amortizationBusiness.compute(this.amortizationMapper.dtoToDomain(amortizationDto)));
    }

}
