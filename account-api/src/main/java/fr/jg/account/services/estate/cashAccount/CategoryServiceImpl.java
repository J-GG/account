package fr.jg.account.services.estate.cashAccount;

import fr.jg.account.domain.estate.cashAccount.Category;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.estate.cashAccount.CategoryMapper;
import fr.jg.account.ports.secondary.estate.cashAccount.CategoryService;
import fr.jg.account.repositories.estate.cashAccount.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public List<Category> getAll() {
        return this.categoryMapper.modelToDomain(this.categoryRepository.findAll(), this.mappingContext);
    }

    @Override
    public Category get(final Long categoryId) {
        return this.categoryRepository.findById(categoryId).map(category -> this.categoryMapper.modelToDomain(category, this.mappingContext)).orElseThrow();
    }
}
