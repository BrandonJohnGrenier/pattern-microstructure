package fm.pattern.microstructure.service;

import fm.pattern.microstructure.Reportable;
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
            return Result.invalid(place).with(Reportable.message("Public places are not currently supported."));
        }

        return repository.save(place);
    }

    public Result<Place> update(Place place) {
        Result<Place> result = validationService.validate(place, Update.class);
        if (result.rejected()) {
            return result;
        }

        if (place.isPublic()) {
            return Result.invalid(place).with(Reportable.message("Public places are not currently supported."));
        }

        return repository.update(place);
    }

    public Result<Place> delete(Place place) {
        Result<Place> result = validationService.validate(place, Delete.class);
        if (result.rejected()) {
            return result;
        }

        if (place.isPublic()) {
            return Result.invalid(place).with(new Reportable("public.places.unsupported", "Public places are not currently supported."));
        }

        return repository.delete(place);
    }

}
