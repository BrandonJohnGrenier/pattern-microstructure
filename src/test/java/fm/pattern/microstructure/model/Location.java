package fm.pattern.microstructure.model;

import javax.validation.constraints.NotNull;

import fm.pattern.microstructure.sequences.CreateLevel1;
import fm.pattern.microstructure.sequences.UpdateLevel1;

public class Location {

    @NotNull(message = "{location.latitude.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    private Double lat;

    @NotNull(message = "{location.longitude.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
    private Double lon;

    public Location() {

    }

    public Location(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}
