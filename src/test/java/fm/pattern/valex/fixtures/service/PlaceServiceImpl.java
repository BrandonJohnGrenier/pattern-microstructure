package fm.pattern.valex.fixtures.service;

import fm.pattern.valex.Result;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.fixtures.model.Place;
import fm.pattern.valex.sequences.Create;
import fm.pattern.valex.sequences.Delete;
import fm.pattern.valex.sequences.Update;

public class PlaceServiceImpl implements PlaceService {

    private final ValidationService validationService;
    private final DataRepository repository;

    public PlaceServiceImpl(ValidationService validationService, DataRepository repository) {
        this.validationService = validationService;
        this.repository = repository;
    }

    public Result<Place> create(Place place) {
        Result<Place> result = validationService.validate(place, Create.class);
        if (result.rejected()) {
            return result;
        }

        if (place.isPublic()) {
            return Result.reject("public.places.unsupported");
        }

        return repository.save(place);
    }

    public Result<Place> update(Place place) {
        Result<Place> result = validationService.validate(place, Update.class);
        if (result.rejected()) {
            return result;
        }

        if (place.isPublic()) {
            return Result.reject("public.places.unsupported");
        }

        return repository.update(place);
    }

    public Result<Place> delete(Place place) {
        Result<Place> result = validationService.validate(place, Delete.class);
        if (result.rejected()) {
            return result;
        }

        if (place.isPublic()) {
            return Result.reject("public.places.unsupported");
        }

        return repository.delete(place);
    }

}
