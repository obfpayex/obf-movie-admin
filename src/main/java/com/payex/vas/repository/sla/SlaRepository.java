package com.payex.vas.repository.sla;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


@NoRepositoryBean
public interface SlaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {

    @NonNull
    List<T> findAll();

    @NonNull
    List<T> findAll(@NonNull Sort var1);

    @NonNull
    List<T> findAllById(@NonNull Iterable<ID> var1);

    @NonNull
    <S extends T> List<S> saveAll(@NonNull Iterable<S> var1);

    void flush();

    <S extends T> S saveAndFlush(S var1);

    void deleteInBatch(Iterable<T> var1);

    void deleteAllInBatch();

    T getOne(ID var1);

    @NonNull
    <S extends T> List<S> findAll(@NonNull Example<S> var1);

    @NonNull
    <S extends T> List<S> findAll(@NonNull Example<S> var1, @NonNull Sort var2);

    @NonNull
    Page<T> findAll(@NonNull Pageable var1);

    @NonNull
    @Override
    <S extends T> Optional<S> findOne(@NonNull Example<S> var1);

    @NonNull
    @Override
    <S extends T> long count(@NonNull Example<S> var1);

    @NonNull
    @Override
    <S extends T> S save(@NonNull S var1);


}
