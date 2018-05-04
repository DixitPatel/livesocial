package com.bigdata.livesocial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Dixit Patel
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class GeometryPojo {

    private String type;

    @JsonProperty("coordinates")
    private List<List<Float>> coordinates;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<Float>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Float>> coordinates) {
        this.coordinates = coordinates;
    }
}
