package com.bigdata.livesocial.model;

/**
 * @author Dixit Patel
 */
public class GeometryPojo {
    private String type;
    private float[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }
}
