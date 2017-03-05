package fm.pattern.validation.fixtures.service;

import fm.pattern.validation.Result;

public class DataRepositoryImpl implements DataRepository {

    private final Session session;

    public DataRepositoryImpl(Session session) {
        this.session = session;
    }

    public <T> Result<T> save(T instance) {
        try {
            session.save(instance);
            return Result.accept(instance);
        }
        catch (Exception e) {
            return Result.reject("system.create.failed", e.getMessage());
        }
    }

    public <T> Result<T> update(T instance) {
        try {
            session.update(instance);
            return Result.accept(instance);
        }
        catch (Exception e) {
            return Result.reject("system.update.failed", e.getMessage());
        }
    }

    public <T> Result<T> delete(T instance) {
        try {
            session.delete(instance);
            return Result.accept(instance);
        }
        catch (Exception e) {
            return Result.reject("system.delete.failed", e.getMessage());
        }
    }

}
