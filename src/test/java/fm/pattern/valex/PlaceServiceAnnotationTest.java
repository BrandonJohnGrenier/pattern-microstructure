package fm.pattern.valex;

import static fm.pattern.valex.PatternAssertions.assertThat;
import static fm.pattern.valex.fixtures.dsl.PlaceDSL.place;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fm.pattern.valex.fixtures.model.Place;
import fm.pattern.valex.fixtures.service.PlaceService;

public class PlaceServiceAnnotationTest extends IntegrationTest {

    @Autowired
    private PlaceService placeService;

    @Test
    public void shouldBeAbleToCreateAPlace() {
        Place place = place().build();
        assertThat(placeService.annotationCreate(place)).accepted();
    }

    @Test
    public void shouldBeAbleToUpdateAPlace() {
        Place place = place().build();
        assertThat(placeService.annotationUpdate(place)).accepted();
    }

    @Test
    public void shouldBeAbleToDeleteAPlace() {
        Place place = place().build();
        assertThat(placeService.annotationDelete(place)).accepted();
    }

    @Test
    public void shouldNotBeAbleToCreateAPlaceAndReturnAResultIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();

        Result<Place> result = placeService.annotationCreate(place);
        assertThat(result).rejected().withError("PLC-1002", "An address is required.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToUpdateAPlaceAndReturnAResultIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();

        Result<Place> result = placeService.annotationUpdate(place);
        assertThat(result).rejected().withError("PLC-1002", "An address is required.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToDeleteAPlaceAndReturnAResultIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();

        Result<Place> result = placeService.annotationDelete(place);
        assertThat(result).rejected().withError("PLC-1002", "An address is required.", UnprocessableEntityException.class);
    }

    @Test
    public void shouldNotBeAbleToActionAPlaceAndReturnAResultIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();

        Result<Place> result = placeService.annotationValid(place);
        assertThat(result).rejected().withError("PLC-1002", "An address is required.", UnprocessableEntityException.class);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void shouldNotBeAbleToCreateAPlaceAndThrowAnExceptionIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();
        placeService.annotationExceptionCreate(place);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void shouldNotBeAbleToUpdateAPlaceAndThrowAnExceptionIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();
        placeService.annotationExceptionUpdate(place);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void shouldNotBeAbleToDeleteAAndThrowAnExceptionIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();
        placeService.annotationExceptionDelete(place);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void shouldNotBeAbleToActionAAndThrowAnExceptionIfThePlaceIsInvalid() {
        Place place = place().withAddress(null).build();
        placeService.annotationExceptionValid(place);
    }

}
