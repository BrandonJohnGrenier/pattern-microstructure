package fm.pattern.microstructure.service;

import fm.pattern.microstructure.Result;
import fm.pattern.microstructure.model.Place;

public interface PlaceService {

    Result<Place> create(Place place);

    Result<Place> update(Place place);

    Result<Place> delete(Place place);

}
