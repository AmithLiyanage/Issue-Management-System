package com.nvoi.Issuemanagementsystem.service;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getEventsByIssueId(int issueId) {
        return eventRepository.findAll();
    }
}
