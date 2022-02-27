package com.nvoi.Issuemanagementsystem.controller;

import com.nvoi.Issuemanagementsystem.model.Event;
import com.nvoi.Issuemanagementsystem.model.Issue;
import com.nvoi.Issuemanagementsystem.model.IssueState;
import com.nvoi.Issuemanagementsystem.model.State;
import com.nvoi.Issuemanagementsystem.repository.EventRepository;
import com.nvoi.Issuemanagementsystem.repository.IssueRepository;
import com.nvoi.Issuemanagementsystem.service.EventService;
import com.nvoi.Issuemanagementsystem.service.IssueService;
import com.nvoi.Issuemanagementsystem.service.IssueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issue")
@CrossOrigin
public class IssueController {

    @Autowired
    private IssueService issueService;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private IssueServiceImpl issueServiceImpl;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    //issue table
    @PostMapping("/add")
    public String add(@RequestBody Issue issue) {
        issueService.saveIssue(issue);

        Event event = new Event();
        event.setIssueId(issue.getIssueId());
        event.setToState(IssueState.OPEN.toString());
        eventService.saveEvent(event);

        return "New Issue is added & event added";
    }

    @GetMapping("/getAllIssues")
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/getOpenIssues")
    public List<Issue> getOpenIssues() {
        return issueRepository.getOpenIssues();
    }

    @GetMapping("/getInProgressIssues")
    public List<Issue> getInProgressIssues() {
        return issueRepository.getInProgressIssues();
    }

    @GetMapping("/getWaitingOnClientIssues")
    public List<Issue> getWaitingOnClientIssues() {
        return issueRepository.getWaitingOnClientIssues();
    }

    @GetMapping("/getResolvedIssues")
    public List<Issue> getResolvedIssues() {
        return issueRepository.getResolvedIssues();
    }

    @GetMapping("/getStatusOfIssues")
    public ResponseEntity<HashMap<String, Object>> getStatusOfIssues() {
        try {
            HashMap<String, Object> customStatusOfIssues = new HashMap<>();
            List<State> states = issueRepository.getStatusOfIssues();
            List<Object> values = new ArrayList<>();
            List<Object> labels = new ArrayList<>();

            for (int i=0; i<states.size(); i++){
                State singleState = states.get(i);
                values.add(singleState.getCount());
                labels.add(singleState.getName());
            }
            customStatusOfIssues.put("values", values);
            customStatusOfIssues.put("labels", labels);
            customStatusOfIssues.put("type", "pie");//nee this for pie chart (as chaert type)

            return new ResponseEntity<>(customStatusOfIssues, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getIssueForUpdate/{issueId}")
    public ResponseEntity<HashMap<String, Object>> getIssueForUpdate(@PathVariable Long issueId) {
        try {
            HashMap<String, Object> customIssue = new HashMap<>();
            Issue issue = issueRepository.findById(issueId).orElseThrow(RuntimeException::new);
            customIssue.put("issue", issue);

            List<Object> nextAvailableStatus = new ArrayList<>();
            switch (issue.getState()) {
                case "OPEN":
                    nextAvailableStatus.add(IssueState.IN_PROGRESS);
                    break;
                case "IN_PROGRESS":
                    nextAvailableStatus.add(IssueState.WAITING_ON_CLIENT);
                    nextAvailableStatus.add(IssueState.RESOLVED);
                    break;
                case "WAITING_ON_CLIENT":
                    nextAvailableStatus.add(IssueState.IN_PROGRESS);
                    nextAvailableStatus.add(IssueState.RESOLVED);
                    break;
                case "RESOLVED"://no updated state after this
                    break;
                default:
                    throw new IllegalStateException("Unexpected value from given Issue: " + issue.getState());
            }
            customIssue.put("availableStatus", nextAvailableStatus);
            return new ResponseEntity<>(customIssue, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateIssue/{issueId}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long issueId, @RequestBody Issue issue) {
        Issue currentIssue = issueRepository.findById(issueId).orElseThrow(RuntimeException::new);
        currentIssue.setIssueName(issue.getIssueName());
        currentIssue.setState(issue.getState());
        issueRepository.save(currentIssue);

        Event event = new Event();
        Event lastEvent = eventRepository.getLastEventBIssueId(issueId);//for get last event
        //System.out.println("Issue : "+ lastEvent.getIssueId());
        event.setIssueId(lastEvent.getIssueId());
        event.setFromState(lastEvent.getToState());
        event.setToState(currentIssue.getState());
        eventService.saveEvent(event);


        return ResponseEntity.ok(currentIssue);
    }

    @DeleteMapping("/deleteIssue/{issueId}")
    public ResponseEntity<Issue> deleteClient(@PathVariable Long issueId) {
        System.out.println("issueId = " + issueId);
        Issue issue = issueService.deleteByIssueId(issueId);
        return ResponseEntity.ok().body(issue);
    }


    @GetMapping("/getEvents/{issueId}")
    public ResponseEntity<List<Event>> get(@PathVariable Long issueId) {
        try {
            List<Event> event = eventService.getEventsByIssueId(issueId);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
