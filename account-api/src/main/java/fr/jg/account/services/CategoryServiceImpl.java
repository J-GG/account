package fr.jg.account.services;

import fr.jg.account.domain.Category;
import fr.jg.account.mappers.CategoryMapper;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.ports.secondary.CategoryService;
import fr.jg.account.repositories.CategoryRepository;
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
        return this.categoryMapper.modelToDomain(this.categoryRepository.findAll(), mappingContext);
    }

    @Override
    public Category get(final Long categoryId) {
        return this.categoryRepository.findById(categoryId).map(category -> this.categoryMapper.modelToDomain(category, mappingContext)).orElseThrow();
    }
}
