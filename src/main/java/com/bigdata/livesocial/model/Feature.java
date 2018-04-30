package com.bigdata.livesocial.model;

import org.geojson.Geometry;

/**
 * @author Dixit Patel
 */
public class Feature {
    private String type;
    private PropertiesPojo description;

    public GeometryPojo getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryPojo geometry) {
        this.geometry = geometry;
    }

    private GeometryPojo geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PropertiesPojo getDescription() {
        return description;
    }

    public void setDescription(PropertiesPojo description) {
        this.description = description;
    }

}
