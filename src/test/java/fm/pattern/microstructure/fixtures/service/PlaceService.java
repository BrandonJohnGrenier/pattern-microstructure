package fm.pattern.microstructure.fixtures.service;

import fm.pattern.microstructure.Result;
import fm.pattern.microstructure.fixtures.model.Place;

public interface PlaceService {

    Result<Place> create(Place place);

    Result<Place> update(Place place);

    Result<Place> delete(Place place);

}
