package fr.jg.account.mappers;

import fr.jg.account.controllers.CompanyController;
import fr.jg.account.domain.Company;
import fr.jg.account.dto.CompanyDto;
import fr.jg.account.models.CompanyModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper {

    public abstract Company modelToDomain(CompanyModel companyModel);

    public abstract List<Company> modelToDomain(List<CompanyModel> companyModels);

    public abstract CompanyDto domainToDto(Company company);

    public abstract List<CompanyDto> domainToDto(List<Company> companies);

    public abstract Company dtoToDomain(CompanyDto companyDto);

    public abstract List<Company> dtoToDomain(List<CompanyDto> companyDtos);

    public abstract CompanyModel domainToModel(Company company);

    public abstract List<CompanyModel> domainToModel(List<Company> companies);

    @AfterMapping
    void afterMappingDomainToDto(final Company company, @MappingTarget final CompanyDto companyDto) {
        try {
            companyDto.add(linkTo(CompanyController.class.getMethod("getCompany", String.class), company.getCode()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
