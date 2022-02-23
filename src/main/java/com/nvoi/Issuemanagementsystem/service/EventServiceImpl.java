package com.nvoi.Issuemanagementsystem.service;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{

    private Event event;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    /*@Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }*/

    public List<Event> getEventsByIssueId(int issueId){

        return eventRepository.findAllByIssueId(issueId);
/*        if (event.getIssueId() == issueId)
            return event;
        return null;*/
    }
}
