package fm.pattern.microstructure;

import static fm.pattern.microstructure.PlatformAssertions.assertThat;
import static fm.pattern.microstructure.ResultType.INTERNAL_ERROR;
import static fm.pattern.microstructure.ResultType.UNPROCESSABLE_ENTITY;
import static fm.pattern.microstructure.dsl.PlaceDSL.place;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Mockito.doThrow;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fm.pattern.microstructure.model.Place;
import fm.pattern.microstructure.service.DataRepository;
import fm.pattern.microstructure.service.DataRepositoryImpl;
import fm.pattern.microstructure.service.PlaceService;
import fm.pattern.microstructure.service.PlaceServiceImpl;
import fm.pattern.microstructure.service.Session;

public class PlaceServiceTest {

    private PlaceService placeService;
    private DataRepository repository;
    private ValidationService validationService;

    @Mock
    private Session session;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.repository = new DataRepositoryImpl(session);

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
    public void shouldNotBeAbleToCreateAPlaceIfTheDatabaseIsUnavailable() {
        Place place = place().build();
        doThrow(new RuntimeException("Some JDBC connection error.")).when(session).save(Mockito.anyObject());

        Result<Place> result = placeService.create(place);
        assertThat(result).rejected().withType(INTERNAL_ERROR).withCode("ERR_INT01").withDescription("Some JDBC connection error.");
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
        assertThat(result).rejected().withType(UNPROCESSABLE_ENTITY).withCode("public.places.unsupported").withDescription("Public places are not currently supported.");
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToUpdateAPlaceIfTheDatabaseIsUnavailable() {
        Place place = place().build();
        doThrow(new RuntimeException("Some JDBC connection error.")).when(session).update(Mockito.anyObject());

        Result<Place> result = placeService.update(place);
        assertThat(result).rejected().withType(INTERNAL_ERROR).withDescription("Some JDBC connection error.");
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

        Result<Place> result = placeService.delete(place);
        assertThat(result).rejected().withType(UNPROCESSABLE_ENTITY).withCode("public.places.unsupported").withDescription("Public places are not currently supported.");
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToDeleteAPlaceIfTheDatabaseIsUnavailable() {
        Place place = place().build();
        doThrow(new RuntimeException("Some JDBC connection error.")).when(session).delete(Mockito.anyObject());

        Result<Place> result = placeService.delete(place);
        assertThat(result).rejected().withType(INTERNAL_ERROR).withDescription("Some JDBC connection error.");
        assertThat(result.getInstance()).isEqualTo(place);
    }

}
