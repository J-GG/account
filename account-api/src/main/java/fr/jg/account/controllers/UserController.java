package fr.jg.account.controllers;

import fr.jg.account.dto.EstateDto;
import fr.jg.account.dto.UserDto;
import fr.jg.account.mappers.EstateMapper;
import fr.jg.account.mappers.UserMapper;
import fr.jg.account.ports.primary.EstateBusiness;
import fr.jg.account.ports.primary.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EstateBusiness estateBusiness;

    @Autowired
    private EstateMapper estateMapper;

    @GetMapping
    public CollectionModel<UserDto> getUsers() {
        return this.userMapper.domainToCollectionModel(this.userBusiness.getAll(), linkTo(UserController.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") final UUID userId) {
        return this.userMapper.domainToDto(this.userBusiness.get(userId));
    }


    @GetMapping("/{id}/estate")
    public EstateDto getEstate(@PathVariable("id") final UUID userId) {
        return this.estateMapper.domainToDto(this.estateBusiness.getByUserId(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@RequestBody final UserDto userDto) {
        return this.userMapper.domainToDto(this.userBusiness.create(this.userMapper.dtoToDomain(userDto)));
    }
}