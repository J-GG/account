package fr.jg.account.mappers;

import java.util.List;

public interface IMapper<T, D, M> {
    D modelToDomain(M model);

    List<D> modelToDomain(List<M> models);

    T domainToDto(D category);

    List<T> domainToDto(List<D> domains);

    D dtoToDomain(T dto);

    List<D> dtoToDomain(List<T> dtos);

    M domainToModel(D domain);

    List<M> domainToModel(List<D> domains);
}
