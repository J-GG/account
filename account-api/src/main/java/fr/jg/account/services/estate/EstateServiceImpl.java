package fr.jg.account.services.estate;

import fr.jg.account.domain.estate.Estate;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.estate.EstateMapper;
import fr.jg.account.ports.secondary.estate.EstateService;
import fr.jg.account.repositories.estate.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EstateServiceImpl implements EstateService {

    @Autowired
    private EstateRepository estateRepository;

    @Autowired
    private EstateMapper estateMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public Estate create(final Estate estate) {
        return this.estateMapper.modelToDomain(this.estateRepository.save(this.estateMapper.domainToModel(estate, this.mappingContext)), this.mappingContext);
    }

    @Override
    public Estate update(final Estate estate) {
        return this.estateMapper.modelToDomain(this.estateRepository.save(this.estateMapper.domainToModel(estate, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<Estate> getAll() {
        return this.estateRepository.findAll().stream().map(account -> this.estateMapper.modelToDomain(account, this.mappingContext)).collect(Collectors.toList());
    }

    @Override
    public Estate get(final UUID estateId) {
        return this.estateRepository.findById(estateId).map(account -> this.estateMapper.modelToDomain(account, this.mappingContext)).orElseThrow();
    }

    @Override
    public Estate getByUserId(final UUID userId) {
        return this.estateMapper.modelToDomain(this.estateRepository.findByUserId(userId), this.mappingContext);
    }

    @Override
    public void delete(final UUID accountId) {
        this.estateRepository.deleteById(accountId);
    }
}
