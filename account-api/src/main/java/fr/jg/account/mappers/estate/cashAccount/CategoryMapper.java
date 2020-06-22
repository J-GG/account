package fr.jg.account.mappers.estate.cashAccount;

import fr.jg.account.controllers.estate.cashAccount.CategoryController;
import fr.jg.account.domain.estate.cashAccount.Category;
import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.estate.cashAccount.CategoryDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.models.estate.cashAccount.CategoryModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper extends AbstractMapper<CategoryDto, Category, CategoryModel> {

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
