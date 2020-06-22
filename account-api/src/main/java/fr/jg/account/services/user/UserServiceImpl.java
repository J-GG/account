package fr.jg.account.services.user;

import fr.jg.account.domain.user.User;
import fr.jg.account.mappers.CycleAvoidingMappingContext;
import fr.jg.account.mappers.user.UserMapper;
import fr.jg.account.ports.secondary.user.UserService;
import fr.jg.account.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CycleAvoidingMappingContext mappingContext;

    @Override
    public User create(final User user) {
        return this.userMapper.modelToDomain(this.userRepository.save(this.userMapper.domainToModel(user, this.mappingContext)), this.mappingContext);
    }

    @Override
    public User update(final User user) {
        return this.userMapper.modelToDomain(this.userRepository.save(this.userMapper.domainToModel(user, this.mappingContext)), this.mappingContext);
    }

    @Override
    public List<User> getAll() {
        return this.userMapper.modelToDomain(this.userRepository.findAll(), this.mappingContext);
    }

    @Override
    public User get(final UUID userId) {
        return this.userRepository.findById(userId).map(user -> this.userMapper.modelToDomain(user, this.mappingContext)).orElseThrow();
    }

    @Override
    public void delete(final UUID userId) {
        this.userRepository.deleteById(userId);
    }
}
