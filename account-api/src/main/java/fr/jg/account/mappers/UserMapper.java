package fr.jg.account.mappers;

import fr.jg.account.controllers.UserController;
import fr.jg.account.domain.User;
import fr.jg.account.dto.LinkedResourceArray;
import fr.jg.account.dto.UserDto;
import fr.jg.account.models.UserModel;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public abstract class UserMapper {

    public abstract User modelToDomain(UserModel userModel, @Context CycleAvoidingMappingContext context);

    public abstract List<User> modelToDomain(List<UserModel> userModels, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "accounts", ignore = true)
    public abstract UserDto domainToDto(User user);

    public abstract List<UserDto> domainToDto(List<User> users);

    @Mapping(target = "accounts", ignore = true)
    public abstract User dtoToDomain(UserDto userDto);

    public abstract List<User> dtoToDomain(List<UserDto> userDtos);

    public abstract UserModel domainToModel(User user, @Context CycleAvoidingMappingContext context);

    public abstract List<UserModel> domainToModel(List<User> users, @Context CycleAvoidingMappingContext context);

    @AfterMapping
    void afterMappingDomainToDto(final User user, @MappingTarget final UserDto userDto) {
        try {
            final LinkedResourceArray linkedAccounts = new LinkedResourceArray(user.getAccounts().size());
            linkedAccounts.add(linkTo(UserController.class.getMethod("getAccounts", UUID.class), user.getId()).withSelfRel());
            userDto.setAccounts(linkedAccounts);

            userDto.add(linkTo(UserController.class.getMethod("getUser", UUID.class), user.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
