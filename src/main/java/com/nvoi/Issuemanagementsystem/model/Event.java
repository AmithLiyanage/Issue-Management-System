package com.nvoi.Issuemanagementsystem.model;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity
public class Event {

    @Id//Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment & generated
    private int eventId;
    private int issueId;
    private ZonedDateTime eventTime;
    private String fromState;
    private String toState;

    public Event() {
        this.eventTime = ZonedDateTime.now(ZoneOffset.UTC);//Eg: 2022-02-23T08:08:16.843809800Z
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public ZonedDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(ZonedDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getFromState() {
        return fromState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public String getToState() {
        return toState;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }
}
