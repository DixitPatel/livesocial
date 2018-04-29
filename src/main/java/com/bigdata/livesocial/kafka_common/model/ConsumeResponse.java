package com.bigdata.livesocial.kafka_common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dixit Patel
 */


public class ConsumeResponse {

    List<Coordinate> coordinates = new ArrayList<>();

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
