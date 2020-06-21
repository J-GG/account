package fr.jg.account.repositories;

import fr.jg.account.models.CompanyModel;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyModel, String> {
}
