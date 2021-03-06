package com.bigdata.livesocial.cassandra.model;

import com.bigdata.livesocial.model.GeoJsonPojo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.UUID;

/**
 * @author Dixit Patel
 */
public class EventDetailsPojo {

    private String event_id;
    private String event_name;
    private String user_name;
    private String event_description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date start_time;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date end_time;
    private String vote;
    private GeoJsonPojo geoJson;
    private String status;
    private String topic;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public GeoJsonPojo getGeoJson() {
        return geoJson;
    }

    public void setGeoJson(GeoJsonPojo geoJson) {
        this.geoJson = geoJson;
    }
}
