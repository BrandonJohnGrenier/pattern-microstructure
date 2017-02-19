package fm.pattern.microstructure.model.service;

import fm.pattern.microstructure.Result;
import fm.pattern.microstructure.ValidationService;
import fm.pattern.microstructure.model.Place;

class PlaceServiceImpl implements PlaceService {

    private final ValidationService validationService;
    
    PlaceServiceImpl(ValidationService validationService) {
        this.validationService = validationService;
    }
    
    public Result<Place> create(Place place) {
        return null;
    }

    public Result<Place> update(Place place) {
        return null;
    }

}
