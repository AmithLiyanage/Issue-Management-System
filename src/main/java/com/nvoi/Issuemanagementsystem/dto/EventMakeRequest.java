package com.nvoi.Issuemanagementsystem.dto;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventMakeRequest {

    private Issue issue;
    private Event event;


    public Object getIssues() {
        return issue.getIssueId();
    }

    public Event getEventsByIssueId(int issueId){
        if (event.getIssueId() == issueId)
            return event;
        return null;
    }
}
