package fm.pattern.valex.fixtures.service;

import org.springframework.stereotype.Service;

import fm.pattern.valex.Result;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.fixtures.model.Place;
import fm.pattern.valex.sequences.Create;
import fm.pattern.valex.sequences.Delete;
import fm.pattern.valex.sequences.Update;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final ValidationService validationService;
    private final DataRepository repository;

    public PlaceServiceImpl(ValidationService validationService, DataRepository repository) {
        this.validationService = validationService;
        this.repository = repository;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Result<Place> annotationCreate(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationUpdate(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationDelete(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationValid(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationExceptionCreate(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationExceptionUpdate(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationExceptionDelete(Place place) {
        return Result.accept(place);
    }

    @Override
    public Result<Place> annotationExceptionValid(Place place) {
        return Result.accept(place);
    }

}
