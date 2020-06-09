package fr.jg.account.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Service;

import java.util.IdentityHashMap;
import java.util.Map;

@Service
public class CycleAvoidingMappingContext {

    private final Map<Object, Object> knownInstances = new IdentityHashMap<>();

    @BeforeMapping
    public <T> T getMappedInstance(final Object source, @TargetType final Class<T> targetType) {
        return targetType.cast(this.knownInstances.get(source));
    }

    @BeforeMapping
    public void storeMappedInstance(final Object source, @MappingTarget final Object target) {
        this.knownInstances.put(source, target);
    }
}