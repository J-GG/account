package fr.jg.account.ports.secondary;

import fr.jg.account.domain.Company;

import java.util.List;

public interface CompanyService {

    Company get(String companyCode);

    List<Company> getAll();
}
