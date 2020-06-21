package fr.jg.account.ports.primary;

import fr.jg.account.domain.Company;

import java.util.List;

public interface CompanyBusiness {

    Company get(String companyCode);

    List<Company> getAll();
}
