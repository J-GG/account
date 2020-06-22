package fr.jg.account.mappers;

import fr.jg.account.dto.LinkedResourceArray;
import org.mapstruct.Context;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMapper<T, D, M> {

    public abstract D modelToDomain(M model, @Context CycleAvoidingMappingContext context);

    public abstract List<D> modelToDomain(List<M> models, @Context CycleAvoidingMappingContext context);

    public abstract T domainToDto(D domain);

    public abstract List<T> domainToDto(List<D> domains);

    public CollectionModel<T> domainToCollectionModel(final List<D> domains, final Link... links) {
        final CollectionModel<T> collectionModel = CollectionModel.of(domainToDto(domains));
        for (final Link link : links) {
            collectionModel.add(link);
        }

        return collectionModel;
    }

    public abstract D dtoToDomain(T dto);

    public abstract List<D> dtoToDomain(List<T> dtos);

    public abstract M domainToModel(D domain, @Context CycleAvoidingMappingContext context);

    public abstract List<M> domainToModel(List<D> domains, @Context CycleAvoidingMappingContext context);

    public LinkedResourceArray domainToLinkedResourceArray(final List<D> domains) {
        return domains != null ? new LinkedResourceArray(domains.size()) : null;
    }

    public List<D> linkedResourceArrayToDomain(final LinkedResourceArray linkedResourceArray) {
        return new ArrayList<>();
    }
}
