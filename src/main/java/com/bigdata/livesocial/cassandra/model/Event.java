package com.bigdata.livesocial.cassandra.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

/**
 * @author Dixit Patel
 */

/*
CREATE TABLE event (
  eventId int PRIMARY KEY,
  lat float,
  long float,
  description text,
  current_time timestamp,
  start timestamp,
  end timestamp
);
 */
@Table(value="event")
public class Event {
    @PrimaryKey(value = "eventId")
    private int eventId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(value = "name")
    private String name;

    @Column(value = "coordinates")
    private String coordinates;

    @Column(value = "description")
    private String description;

    @Column(value = "current_time")
    private Date current_time;

    @Column(value = "start")
    private Date startTime;

    @Column(value = "end")
    private Date endTime;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Date current_time) {
        this.current_time = current_time;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
