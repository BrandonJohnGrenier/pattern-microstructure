package fm.pattern.microstructure;

import static fm.pattern.microstructure.PlatformAssertions.assertThat;
import static fm.pattern.microstructure.ResultType.UNPROCESSABLE_ENTITY;
import static fm.pattern.microstructure.dsl.PlaceDSL.place;
import static org.assertj.core.api.StrictAssertions.assertThat;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.microstructure.model.Place;
import fm.pattern.microstructure.service.DataRepository;
import fm.pattern.microstructure.service.DataRepositoryImpl;
import fm.pattern.microstructure.service.PlaceService;
import fm.pattern.microstructure.service.PlaceServiceImpl;

public class PlaceServiceTest {

    private PlaceService placeService;
    private DataRepository repository;
    private ValidationService validationService;

    @Before
    public void before() {
        this.repository = new DataRepositoryImpl();
        this.validationService = new SimpleValidationService(Validation.buildDefaultValidatorFactory().getValidator());
        this.placeService = new PlaceServiceImpl(validationService, repository);
    }

    @Test
    public void shouldBeAbleToCreateAPlace() {
        Place place = place().build();

        Result<Place> result = placeService.create(place);
        assertThat(result).accepted().withType(ResultType.CREATED);
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaceIfThePlaceIsPublic() {
        Place place = place().build();
        place.setPublic(true);

        Result<Place> result = placeService.create(place);
        assertThat(result).rejected().withType(UNPROCESSABLE_ENTITY).withDescription("Public places are not currently supported.");
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldBeAbleToUpdateAPlace() {
        Place place = place().build();

        Result<Place> result = placeService.update(place);
        assertThat(result).accepted().withType(ResultType.UPDATED);
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToUpdateAPlaceIfThePlaceIsPublic() {
        Place place = place().build();
        place.setPublic(true);

        Result<Place> result = placeService.update(place);
        assertThat(result).rejected().withType(UNPROCESSABLE_ENTITY).withDescription("Public places are not currently supported.");
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldBeAbleToDeleteAPlace() {
        Place place = place().build();

        Result<Place> result = placeService.delete(place);
        assertThat(result).accepted().withType(ResultType.DELETED);
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToDeleteAPlaceIfThePlaceIsPublic() {
        Place place = place().build();
        place.setPublic(true);

        Result<Place> result = placeService.update(place);
        assertThat(result).rejected().withType(UNPROCESSABLE_ENTITY).withDescription("Public places are not currently supported.");
        assertThat(result.getInstance()).isEqualTo(place);
    }

}
