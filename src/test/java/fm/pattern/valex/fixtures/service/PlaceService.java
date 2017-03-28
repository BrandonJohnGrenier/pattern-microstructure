package fm.pattern.valex.fixtures.service;

import fm.pattern.valex.Result;
import fm.pattern.valex.annotations.Create;
import fm.pattern.valex.annotations.Delete;
import fm.pattern.valex.annotations.Update;
import fm.pattern.valex.annotations.Valid;
import fm.pattern.valex.fixtures.model.Place;

public interface PlaceService {

    Result<Place> create(Place place);

    Result<Place> update(Place place);

    Result<Place> delete(Place place);

    Result<Place> annotationCreate(@Create Place place);

    Result<Place> annotationUpdate(@Update Place place);

    Result<Place> annotationDelete(@Delete Place place);

    Result<Place> annotationValid(@Valid(fm.pattern.valex.sequences.Create.class) Place place);

    Result<Place> annotationExceptionCreate(@Create(throwException = true) Place place);

    Result<Place> annotationExceptionUpdate(@Update(throwException = true) Place place);

    Result<Place> annotationExceptionDelete(@Delete(throwException = true) Place place);

    Result<Place> annotationExceptionValid(@Valid(value = fm.pattern.valex.sequences.Create.class, throwException = true) Place place);

}
