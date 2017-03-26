package fm.pattern.valex.fixtures.service;

import fm.pattern.valex.Result;
import fm.pattern.valex.fixtures.model.Place;

public interface PlaceService {

    Result<Place> create(Place place);

    Result<Place> update(Place place);

    Result<Place> delete(Place place);

    Result<Place> annotationCreate(Place place);

    Result<Place> annotationUpdate(Place place);

    Result<Place> annoationDelete(Place place);
    
}
