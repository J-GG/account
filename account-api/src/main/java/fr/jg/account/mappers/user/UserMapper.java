package fr.jg.account.mappers.user;

import fr.jg.account.controllers.estate.EstateController;
import fr.jg.account.controllers.user.UserController;
import fr.jg.account.domain.user.User;
import fr.jg.account.dto.LinkedResource;
import fr.jg.account.dto.user.UserDto;
import fr.jg.account.mappers.AbstractMapper;
import fr.jg.account.mappers.estate.EstateMapper;
import fr.jg.account.models.user.UserModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Mapper(componentModel = "spring", uses = EstateMapper.class)
public abstract class UserMapper extends AbstractMapper<UserDto, User, UserModel> {

    @AfterMapping
    void afterMappingDomainToDto(final User user, @MappingTarget final UserDto userDto) {
        try {
            if (user.getEstate() != null) {
                final LinkedResource<UUID> linkedEstate = new LinkedResource<>(user.getEstate().getId());
                linkedEstate.add(linkTo(EstateController.class.getMethod("getEstate", UUID.class), user.getEstate().getId()).withSelfRel());
                userDto.setEstate(linkedEstate);
            }

            userDto.add(linkTo(UserController.class.getMethod("getUser", UUID.class), user.getId()).withSelfRel());
        } catch (final NoSuchMethodException ex) {
            ex.printStackTrace();
        }
    }
}
