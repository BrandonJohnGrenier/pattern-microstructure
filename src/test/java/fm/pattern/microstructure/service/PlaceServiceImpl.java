package fm.pattern.microstructure.service;

import fm.pattern.microstructure.Result;
import fm.pattern.microstructure.ValidationService;
import fm.pattern.microstructure.model.Place;
import fm.pattern.microstructure.sequences.Create;
import fm.pattern.microstructure.sequences.Delete;
import fm.pattern.microstructure.sequences.Update;

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
