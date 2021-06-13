package com.hladchuk.internet_Shop.service;

import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;

public interface AbstractService <T>{
    public T save(@NonNull T object);

    public void removeById(@NonNull Integer id);

    public Optional<T> findById(@NonNull Integer id);

    public @NonNull Collection<T> findAll();
}
