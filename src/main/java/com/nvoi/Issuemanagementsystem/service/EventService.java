package com.nvoi.Issuemanagementsystem.service;

import com.nvoi.Issuemanagementsystem.model.Event;

import java.util.List;

public interface EventService {
    public Event saveEvent(Event event);
    public List<Event> getEventsByIssueId(int issueId);
}
