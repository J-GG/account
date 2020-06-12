package fr.jg.account.controllers;

import fr.jg.account.domain.Category;
import fr.jg.account.dto.CategoryDto;
import fr.jg.account.mappers.CategoryMapper;
import fr.jg.account.ports.primary.CategoryBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryBusiness categoryBusiness;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    public CollectionModel<CategoryDto> getCategories() {
        final CollectionModel<CategoryDto> categoryDtos = CollectionModel.of(this.categoryMapper.domainToDto(this.categoryBusiness.getAll()));
        categoryDtos.add(linkTo(CategoryController.class).withSelfRel());

        return categoryDtos;
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") final Long categoryId) {
        return this.categoryMapper.domainToDto(this.categoryBusiness.get(categoryId));
    }

    @GetMapping("/{id}/children")
    public CollectionModel<CategoryDto> getCategoryChildren(@PathVariable("id") final Long categoryId) throws NoSuchMethodException {
        final List<Category> category = this.categoryBusiness.get(categoryId).getChildren();
        final CollectionModel<CategoryDto> categoryDtos = CollectionModel.of(this.categoryMapper.domainToDto(category));
        categoryDtos.add(linkTo(CategoryController.class.getMethod("getCategoryChildren", Long.class), categoryId).withSelfRel());

        return categoryDtos;
    }
}