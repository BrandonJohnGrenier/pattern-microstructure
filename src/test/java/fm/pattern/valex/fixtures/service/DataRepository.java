package fm.pattern.valex.fixtures.service;

import fm.pattern.valex.Result;

public interface DataRepository {

    <T> Result<T> save(T instance);

    <T> Result<T> update(T instance);

    <T> Result<T> delete(T instance);

}
