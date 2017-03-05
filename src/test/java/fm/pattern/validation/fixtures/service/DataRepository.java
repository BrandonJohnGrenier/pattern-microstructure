package fm.pattern.validation.fixtures.service;

import fm.pattern.validation.Result;

public interface DataRepository {

    <T> Result<T> save(T instance);

    <T> Result<T> update(T instance);

    <T> Result<T> delete(T instance);

}
