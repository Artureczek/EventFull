package com.sii.eventfull.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A user.
 */
@Entity
@Table(name = "EVENT")

public class Event extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, name = "event_name")
    private String eventName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "event_location" ,length = 100)
    private String eventLocation;

    @NotNull
    @Column(name = "location_longitude")
    private Float locationLongitude;

    @NotNull
    @Column(name = "location_latitude")
    private Float locationLatitude;

    @NotNull
    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, name = "event_type")
    private String eventType;

    @NotNull
    @Size(min = 1, max = 500)
    @Column(length = 500, name = "event_description")
    private String eventDescription;


    public Event(String eventName, String eventLocation, Float locationLongitude, Float locationLatitude,
                 LocalDateTime eventDate, String eventType, String eventDescription) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }

    public Event() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Float getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Float locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public Float getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Float locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;
        return !(event.getId() == null || getId() == null) && Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


    @Override
    public String toString() {
        return "Event{" +
            "id=" + id +
            ", eventName='" + eventName + '\'' +
            ", eventLocation='" + eventLocation + '\'' +
            ", locationLongitude=" + locationLongitude +
            ", locationLatitude=" + locationLatitude +
            ", eventDate=" + eventDate +
            ", eventType='" + eventType + '\'' +
            ", eventDescription='" + eventDescription + '\'' +
            '}';
    }
}
