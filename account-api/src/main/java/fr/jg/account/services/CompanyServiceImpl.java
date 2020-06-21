package fr.jg.account.services;

import fr.jg.account.domain.Company;
import fr.jg.account.mappers.CompanyMapper;
import fr.jg.account.ports.secondary.CompanyService;
import fr.jg.account.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company get(final String companyCode) {
        return this.companyRepository.findById(companyCode).map(this.companyMapper::modelToDomain).orElseThrow();
    }

    @Override
    public List<Company> getAll() {
        return StreamSupport.stream(this.companyRepository.findAll().spliterator(), true).map(this.companyMapper::modelToDomain).collect(Collectors.toList());
    }

}
