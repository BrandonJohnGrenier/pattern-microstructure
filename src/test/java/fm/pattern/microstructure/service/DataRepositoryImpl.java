package fm.pattern.microstructure.service;

import fm.pattern.microstructure.Reportable;
import fm.pattern.microstructure.Result;

public class DataRepositoryImpl implements DataRepository {

    private final Session session;

    public DataRepositoryImpl(Session session) {
        this.session = session;
    }

    public <T> Result<T> save(T instance) {
        try {
            session.save(instance);
            return Result.created(instance);
        }
        catch (Exception e) {
            return Result.internal_error(instance).with(new Reportable("ACC-4000", e.getMessage()));
        }
    }

    public <T> Result<T> update(T instance) {
        try {
            session.update(instance);
            return Result.updated(instance);
        }
        catch (Exception e) {
            return Result.internal_error(instance).with(new Reportable("ACC-4001", e.getMessage()));
        }
    }

    public <T> Result<T> delete(T instance) {
        try {
            session.delete(instance);
            return Result.deleted(instance);
        }
        catch (Exception e) {
            return Result.internal_error(instance).with(new Reportable("ACC-4002", e.getMessage()));
        }
    }

}
