package fm.pattern.microstructure.service;

import fm.pattern.microstructure.Consumable;
import fm.pattern.microstructure.Result;

public class DataRepositoryImpl implements DataRepository {

    public DataRepositoryImpl() {

    }

    public <T> Result<T> save(T instance) {
        try {
            // session.save(instance);
            return Result.created(instance);
        }
        catch (Exception e) {
            return Result.internal_error(new Consumable("ERR_INT01", e.getMessage()));
        }
    }

    public <T> Result<T> update(T instance) {
        try {
            // session.update(instance);
            return Result.updated(instance);
        }
        catch (Exception e) {
            return Result.internal_error(e.getMessage());
        }
    }

    public <T> Result<T> delete(T instance) {
        try {
            // session.delete(instance);
            return Result.deleted(instance);
        }
        catch (Exception e) {
            return Result.internal_error(e.getMessage());
        }
    }

}
