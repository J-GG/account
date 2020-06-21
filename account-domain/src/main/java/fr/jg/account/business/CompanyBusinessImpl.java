package fr.jg.account.business;

import fr.jg.account.domain.Company;
import fr.jg.account.ports.primary.CompanyBusiness;
import fr.jg.account.ports.secondary.CompanyService;

import java.util.List;

public class CompanyBusinessImpl implements CompanyBusiness {

    private final CompanyService companyService;

    public CompanyBusinessImpl(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public Company get(final String companyCode) {
        return this.companyService.get(companyCode);
    }

    @Override
    public List<Company> getAll() {
        return this.companyService.getAll();
    }
}
