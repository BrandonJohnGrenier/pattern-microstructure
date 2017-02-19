package fm.pattern.validation.model.service;

import fm.pattern.validation.Result;
import fm.pattern.validation.ValidationService;
import fm.pattern.validation.model.Place;

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
