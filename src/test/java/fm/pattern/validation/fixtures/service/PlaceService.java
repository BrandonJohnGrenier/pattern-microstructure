package fm.pattern.validation.fixtures.service;

import fm.pattern.validation.Result;
import fm.pattern.validation.fixtures.model.Place;

public interface PlaceService {

    Result<Place> create(Place place);

    Result<Place> update(Place place);

    Result<Place> delete(Place place);

}
