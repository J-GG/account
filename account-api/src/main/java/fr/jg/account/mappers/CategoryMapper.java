package fr.jg.account.mappers;

import fr.jg.account.controllers.CategoryController;
import fr.jg.account.domain.Category;
import fr.jg.account.dto.CategoryDto;
import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.models.CategoryModel;
import org.mapstruct.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract Category modelToDomain(CategoryModel categoryModel, @Context CycleAvoidingMappingContext context);

    public abstract List<Category> modelToDomain(List<CategoryModel> categoryModels, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "children", ignore = true)
    public abstract CategoryDto domainToDto(Category category);

    public abstract List<CategoryDto> domainToDto(List<Category> categories);

    @Mapping(target = "children", ignore = true)
    public abstract Category dtoToDomain(CategoryDto categoryDtos);

    public abstract List<Category> dtoToDomain(List<CategoryDto> categoryDtos);

    public abstract CategoryModel domainToModel(Category category, @Context CycleAvoidingMappingContext context);

    public abstract List<CategoryModel> domainToModel(List<Category> categories, @Context CycleAvoidingMappingContext context);

    @AfterMapping
    void afterMappingDomainToDto(final Category category, @MappingTarget final CategoryDto categoryDto) {
        try {
            if (categoryDto.getParent() != null) {
                categoryDto.getParent().add(linkTo(CategoryController.class.getMethod("getCategory", Long.class), category.getParent().getId()).withSelfRel());
            }

            final LinkedResourceArray linkedChildren = new LinkedResourceArray(category.getChildren().size());
            linkedChildren.add(linkTo(CategoryController.class.getMethod("getCategoryChildren", Long.class), category.getId()).withSelfRel());
            categoryDto.setChildren(linkedChildren);

            categoryDto.add(linkTo(CategoryController.class.getMethod("getCategory", Long.class), category.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
