package fr.jg.account.services.estate.tradingAccount;

import fr.jg.account.domain.estate.tradingAccount.Stock;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.estate.tradingAccount.StockMapper;
import fr.jg.account.ports.secondary.estate.tradingAccount.StockService;
import fr.jg.account.repositories.estate.tradingAccount.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public Stock create(final Stock stock) {
        return this.stockMapper.modelToDomain(this.stockRepository.save(this.stockMapper.domainToModel(stock, this.mappingContext)), this.mappingContext);
    }

    @Override
    public Stock get(final String companyCode) {
        return this.stockRepository.findById(companyCode).map(stock -> this.stockMapper.modelToDomain(stock, this.mappingContext)).orElseThrow();
    }

    @Override
    public List<Stock> getAll() {
        return StreamSupport.stream(this.stockRepository.findAll().spliterator(), true)
                .map(stock -> this.stockMapper.modelToDomain(stock, this.mappingContext))
                .collect(Collectors.toList());
    }

}
