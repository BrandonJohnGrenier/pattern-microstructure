package fm.pattern.microstructure.service;

import fm.pattern.microstructure.Result;

public interface DataRepository {

    <T> Result<T> save(T instance);

    <T> Result<T> update(T instance);

    <T> Result<T> delete(T instance);

}
