package fr.jg.account.controllers;

import fr.jg.account.domain.User;
import fr.jg.account.dto.AccountDto;
import fr.jg.account.dto.UserDto;
import fr.jg.account.mappers.AccountMapper;
import fr.jg.account.mappers.UserMapper;
import fr.jg.account.ports.primary.AccountBusiness;
import fr.jg.account.ports.primary.UserBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
    private AccountBusiness accountBusiness;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    MessageSource messageSource;

    @GetMapping
    public CollectionModel<UserDto> getUsers() {
        final CollectionModel<UserDto> userDtos = CollectionModel.of(this.userMapper.domainToDto(this.userBusiness.getAll()));
        userDtos.add(linkTo(UserController.class).withSelfRel());

        return userDtos;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") final UUID userId) {
        return this.userMapper.domainToDto(this.userBusiness.get(userId));
    }


    @GetMapping("/{id}/accounts")
    public CollectionModel<AccountDto> getAccounts(@PathVariable("id") final UUID userId) throws NoSuchMethodException {
        final CollectionModel<AccountDto> accountDtos = CollectionModel.of(this.accountMapper.domainToDto(this.accountBusiness.getByUserId(userId)));
        accountDtos.add(linkTo(UserController.class.getMethod("getUser", UUID.class), userId).withRel("user"));
        accountDtos.add(linkTo(UserController.class.getMethod("getAccounts", UUID.class), userId).withSelfRel());

        return accountDtos;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@RequestBody final UserDto userDto) {
        return this.userMapper.domainToDto(this.userBusiness.create(this.userMapper.dtoToDomain(userDto)));
    }

    @PostMapping("/{id}/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionModel<AccountDto> postAccountToUser(@PathVariable("id") final UUID userId, @RequestBody final AccountDto accountDto) throws NoSuchMethodException {
        final User user = this.userBusiness.addAccountToUser(userId, this.accountMapper.dtoToDomain(accountDto));

        return this.getAccounts(user.getId());
    }
}