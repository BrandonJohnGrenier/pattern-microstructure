package fm.pattern.validation.model.service;

import fm.pattern.validation.Result;
import fm.pattern.validation.model.Place;

public interface PlaceService {

    Result<Place> create(Place place);
    
    Result<Place> update(Place place);
    
}
