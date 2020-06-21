package fr.jg.account.controllers;

import fr.jg.account.dto.CompanyDto;
import fr.jg.account.mappers.CompanyMapper;
import fr.jg.account.ports.primary.CompanyBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyBusiness companyBusiness;

    @Autowired
    CompanyMapper companyMapper;

    @GetMapping
    public CollectionModel<CompanyDto> getCompanies() {
        final CollectionModel<CompanyDto> companyDtos = CollectionModel.of(this.companyMapper.domainToDto(this.companyBusiness.getAll()));
        companyDtos.add(linkTo(CompanyController.class).withSelfRel());

        return companyDtos;
    }

    @GetMapping("/{code}")
    public CompanyDto getCompany(@PathVariable("code") final String companyCode) {
        return this.companyMapper.domainToDto(this.companyBusiness.get(companyCode));
    }
}