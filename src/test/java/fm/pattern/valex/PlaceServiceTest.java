package fm.pattern.valex;

import static fm.pattern.valex.PatternAssertions.assertThat;
import static fm.pattern.valex.dsl.PlaceDSL.place;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fm.pattern.valex.fixtures.model.Place;
import fm.pattern.valex.fixtures.service.DataRepository;
import fm.pattern.valex.fixtures.service.DataRepositoryImpl;
import fm.pattern.valex.fixtures.service.PlaceService;
import fm.pattern.valex.fixtures.service.PlaceServiceImpl;
import fm.pattern.valex.fixtures.service.Session;

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
        assertThat(result).accepted();
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaceIfThePlaceIsPublic() {
        Place place = place().build();
        place.setPublic(true);

        Result<Place> result = placeService.create(place);
        assertThat(result).rejected().withError("ADD-2000", "Public places are not currently supported.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaceIfTheDatabaseIsUnavailable() {
        Place place = place().build();
        doThrow(new RuntimeException("Some JDBC connection error.")).when(session).save(Mockito.anyObject());

        Result<Place> result = placeService.create(place);
        assertThat(result).rejected().withError("SYS-0003", "The create operation failed due to an internal system error: Some JDBC connection error.", InternalErrorException.class);
    }

    @Test
    public void shouldBeAbleToUpdateAPlace() {
        Place place = place().build();

        Result<Place> result = placeService.update(place);
        assertThat(result).accepted();
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToUpdateAPlaceIfThePlaceIsPublic() {
        Place place = place().build();
        place.setPublic(true);

        Result<Place> result = placeService.update(place);
        assertThat(result).rejected().withError("ADD-2000", "Public places are not currently supported.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAPlaceIfTheDatabaseIsUnavailable() {
        Place place = place().build();
        doThrow(new RuntimeException("Some JDBC connection error.")).when(session).update(Mockito.anyObject());

        Result<Place> result = placeService.update(place);
        assertThat(result).rejected().withError("SYS-0004", "The update operation failed due to an internal system error: Some JDBC connection error.", InternalErrorException.class);
    }

    @Test
    public void shouldBeAbleToDeleteAPlace() {
        Place place = place().build();

        Result<Place> result = placeService.delete(place);
        assertThat(result).accepted();
        assertThat(result.getInstance()).isEqualTo(place);
    }

    @Test
    public void shouldNotBeAbleToDeleteAPlaceIfThePlaceIsPublic() {
        Place place = place().build();
        place.setPublic(true);

        Result<Place> result = placeService.delete(place);
        assertThat(result).rejected().withError("ADD-2000", "Public places are not currently supported.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToDeleteAPlaceIfTheDatabaseIsUnavailable() {
        Place place = place().build();
        doThrow(new RuntimeException("Some JDBC connection error.")).when(session).delete(Mockito.anyObject());

        Result<Place> result = placeService.delete(place);
        assertThat(result).rejected().withError("SYS-0005", "The delete operation failed due to an internal system error: Some JDBC connection error.", InternalErrorException.class);
    }

}
